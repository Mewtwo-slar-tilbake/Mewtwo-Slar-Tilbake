package com.kaijumon.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.backends.lwjgl3.*;
import com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio;
import com.badlogic.gdx.backends.lwjgl3.audio.mock.MockAudio;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.Configuration;

import java.io.File;
import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;

public class WindowlessApplication implements Lwjgl3ApplicationBase {

    final Array<Lwjgl3Window> windows = new Array<Lwjgl3Window>();
    private volatile boolean running = true;

    public WindowlessApplication(ApplicationListener listener) {
        initializeGlfw();
        createGlfwWindow(0);

        Gdx.app = this;
        // Disable audio
        Gdx.audio = new MockAudio();
        Gdx.files = new HeadlessFiles();//this.files = Gdx.files = createFiles();
        Gdx.net = new Lwjgl3Net(new Lwjgl3ApplicationConfiguration());//this.net = Gdx.net = new Lwjgl3Net(config);
        Gdx.graphics = mock(Graphics.class);
        Gdx.gl20 = mock(GL20.class);
        //this.clipboard = new Lwjgl3Clipboard();

        //this.sync = new Sync();

        //Lwjgl3Window window = createWindow(listener, 0);
        //windows.add(window);
        try {
            loop();
            //cleanupWindows();
        } catch (Throwable t) {
            if (t instanceof RuntimeException)
                throw (RuntimeException)t;
            else
                throw new GdxRuntimeException(t);
        } finally {
            cleanup();
        }
    }

    private void initializeGlfw() {
        if (SharedLibraryLoader.isMac) loadGlfwAwtMacos();
        Lwjgl3NativesLoader.load();
        GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(Lwjgl3ApplicationConfiguration.errorStream);
        GLFW.glfwSetErrorCallback(errorCallback);
        if (SharedLibraryLoader.isMac) GLFW.glfwInitHint(GLFW.GLFW_ANGLE_PLATFORM_TYPE, GLFW.GLFW_ANGLE_PLATFORM_TYPE_METAL);
        GLFW.glfwInitHint(GLFW.GLFW_JOYSTICK_HAT_BUTTONS, GLFW.GLFW_FALSE);
        if (!GLFW.glfwInit()) {
            throw new GdxRuntimeException("Unable to initialize GLFW");
        }
    }

    static void loadGlfwAwtMacos () {
        try {
            Class loader = Class.forName("com.badlogic.gdx.backends.lwjgl3.awt.GlfwAWTLoader");
            Method load = loader.getMethod("load");
            File sharedLib = (File)load.invoke(loader);
            Configuration.GLFW_LIBRARY_NAME.set(sharedLib.getAbsolutePath());
            Configuration.GLFW_CHECK_THREAD0.set(false);
        } catch (ClassNotFoundException t) {
            return;
        } catch (Throwable t) {
            throw new GdxRuntimeException("Couldn't load GLFW AWT for macOS.", t);
        }
    }

    private Lwjgl3Window createWindow(ApplicationListener listener, final long sharedContext) {
        //final Lwjgl3Window window = new Lwjgl3Window(listener, new Lwjgl3ApplicationConfiguration(), this);
        if (sharedContext == 0) {
            // the main window is created immediately
            //createWindow(window, config, sharedContext);
        } else {
            // creation of additional windows is deferred to avoid GL context trouble
            postRunnable(new Runnable() {
                public void run () {
                    //createWindow(window, sharedContext);
                    //windows.add(window);
                }
            });
        }
        return null;
    }

    void createWindow (Lwjgl3Window window, long sharedContext) {
        long windowHandle = createGlfwWindow(sharedContext);
        //window.create(windowHandle);
        window.setVisible(false);

        for (int i = 0; i < 2; i++) {
            GL11.glClearColor(0, 0, 0, 0);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            GLFW.glfwSwapBuffers(windowHandle);
        }
    }

    static long createGlfwWindow(long sharedContextWindow) {
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_AUTO_ICONIFY, GLFW.GLFW_FALSE);

        GLFW.glfwWindowHint(GLFW.GLFW_RED_BITS, 8);
        GLFW.glfwWindowHint(GLFW.GLFW_GREEN_BITS, 8);
        GLFW.glfwWindowHint(GLFW.GLFW_BLUE_BITS, 8);
        GLFW.glfwWindowHint(GLFW.GLFW_ALPHA_BITS, 8);
        GLFW.glfwWindowHint(GLFW.GLFW_STENCIL_BITS, 0);
        GLFW.glfwWindowHint(GLFW.GLFW_DEPTH_BITS, 16);
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 0);

        long windowHandle = 0;

        GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
        windowHandle = GLFW.glfwCreateWindow(10, 10, "", 0, sharedContextWindow);

        if (windowHandle == 0) {
            throw new GdxRuntimeException("Couldn't create window");
        }

        //Lwjgl3Window.setSizeLimits(windowHandle, 10, 10, 10, 10);
        GLFW.glfwSetWindowPos(windowHandle, 0, 0);
        GLFW.glfwMakeContextCurrent(windowHandle);
        GLFW.glfwSwapInterval(0);
        GL.createCapabilities();

        //initiateGL();

        return windowHandle;
    }

    private static void initiateGL () {
        //if (!useGLES20) {
        String versionString = GL11.glGetString(GL11.GL_VERSION);
        String vendorString = GL11.glGetString(GL11.GL_VENDOR);
        String rendererString = GL11.glGetString(GL11.GL_RENDERER);
            //glVersion = new GLVersion(Application.ApplicationType.Desktop, versionString, vendorString, rendererString);
            /*
        } else {
            try {
                Class gles = Class.forName("org.lwjgl.opengles.GLES20");
                Method getString = gles.getMethod("glGetString", int.class);
                String versionString = (String)getString.invoke(gles, GL11.GL_VERSION);
                String vendorString = (String)getString.invoke(gles, GL11.GL_VENDOR);
                String rendererString = (String)getString.invoke(gles, GL11.GL_RENDERER);
                glVersion = new GLVersion(Application.ApplicationType.Desktop, versionString, vendorString, rendererString);
            } catch (Throwable e) {
                throw new GdxRuntimeException("Couldn't get GLES version string.", e);
            }
        }*/
    }

    protected void loop () {
        Array<Lwjgl3Window> closedWindows = new Array<Lwjgl3Window>();
        while (running && windows.size > 0) {
            //audio.update();

            boolean haveWindowsRendered = false;
            closedWindows.clear();
            /*
            for (Lwjgl3Window window : windows) {
                synchronized (lifecycleListeners) {
                    haveWindowsRendered |= window.update();
                }
                if (window.shouldClose()) {
                    closedWindows.add(window);
                }
            }*/
            GLFW.glfwPollEvents();

            boolean shouldRequestRendering;
            /*
            synchronized (runnables) {
                shouldRequestRendering = runnables.size > 0;
                executedRunnables.clear();
                executedRunnables.addAll(runnables);
                runnables.clear();
            }*/
            /*
            for (Runnable runnable : executedRunnables) {
                runnable.run();
            }*/
            /*
            if (shouldRequestRendering) {
                // Must follow Runnables execution so changes done by Runnables are reflected
                // in the following render.
                for (Lwjgl3Window window : windows) {
                    if (!window.getGraphics().isContinuousRendering()) window.requestRendering();
                }
            }

            for (Lwjgl3Window closedWindow : closedWindows) {
                if (windows.size == 1) {
                    // Lifecycle listener methods have to be called before ApplicationListener methods. The
                    // application will be disposed when _all_ windows have been disposed, which is the case,
                    // when there is only 1 window left, which is in the process of being disposed.
                    for (int i = lifecycleListeners.size - 1; i >= 0; i--) {
                        LifecycleListener l = lifecycleListeners.get(i);
                        l.pause();
                        l.dispose();
                    }
                    lifecycleListeners.clear();
                }
                closedWindow.dispose();

                windows.removeValue(closedWindow, false);
            }*/
            /*
            if (!haveWindowsRendered) {
                // Sleep a few milliseconds in case no rendering was requested
                // with continuous rendering disabled.
                try {
                    Thread.sleep(1000 / config.idleFPS);
                } catch (InterruptedException e) {
                    // ignore
                }
            } else if (targetFramerate > 0) {
                sync.sync(targetFramerate); // sleep as needed to meet the target framerate
            }*/
        }
    }

    @Override
    public Lwjgl3Audio createAudio (Lwjgl3ApplicationConfiguration config) {
        return new MockAudio();
    }

    @Override
    public Lwjgl3Input createInput(Lwjgl3Window window) {
        return null;
    }

    protected void cleanup () {
        GLFW.glfwTerminate();
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Audio getAudio() {
        return null;
    }

    @Override
    public Input getInput() {
        return null;
    }

    @Override
    public Files getFiles() {
        return null;
    }

    @Override
    public Net getNet() {
        return null;
    }

    @Override
    public void log(String tag, String message) {

    }

    @Override
    public void log(String tag, String message, Throwable exception) {

    }

    @Override
    public void error(String tag, String message) {

    }

    @Override
    public void error(String tag, String message, Throwable exception) {

    }

    @Override
    public void debug(String tag, String message) {

    }

    @Override
    public void debug(String tag, String message, Throwable exception) {

    }

    @Override
    public void setLogLevel(int logLevel) {

    }

    @Override
    public int getLogLevel() {
        return 0;
    }

    @Override
    public void setApplicationLogger(ApplicationLogger applicationLogger) {

    }

    @Override
    public ApplicationLogger getApplicationLogger() {
        return null;
    }

    @Override
    public ApplicationType getType() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public long getJavaHeap() {
        return 0;
    }

    @Override
    public long getNativeHeap() {
        return 0;
    }

    @Override
    public Preferences getPreferences(String name) {
        return null;
    }

    @Override
    public Clipboard getClipboard() {
        return null;
    }

    @Override
    public void postRunnable(Runnable runnable) {

    }

    @Override
    public void exit() {

    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }
}
