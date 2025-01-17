package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.headless.*;
import com.badlogic.gdx.backends.headless.mock.audio.MockAudio;
import com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.ObjectMap;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import static com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application.createGlfwWindow;
import static com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application.initializeGlfw;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

/**
 * Similar to {@link HeadlessApplication}, except that it provides a real OpenGL context using LWJGL3. This makes it
 * possible to test things that use features that depend on OpenGL, like a {@link SpriteBatch}.
 */
public class TestApplication implements Application, Lwjgl3ApplicationBase {
    private final ObjectMap<String, Preferences> preferences = new ObjectMap<>();
    private final Array<Runnable> runnables = new Array<>();
    private final Array<Runnable> executedRunnables = new Array<>();
    private final Array<LifecycleListener> lifecycleListeners = new Array<>();
    private final ApplicationListener applicationListener;
    private final Audio audio;
    private final Files files;
    private final Net net;
    private final Lwjgl3Window window;
    protected boolean running = true;
    private ApplicationLogger applicationLogger;
    private int logLevel = LOG_INFO;

    public TestApplication(final ApplicationListener applicationListener) {
        HeadlessNativesLoader.load();

        Lwjgl3NativesLoader.load();

        initializeGlfw();
        GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        setApplicationLogger(new HeadlessApplicationLogger());

        this.applicationListener = applicationListener;

        Gdx.app = this;
        Gdx.audio = this.audio = new MockAudio();
        Gdx.files = this.files = new HeadlessFiles();
        Gdx.net = this.net = new HeadlessNet(new HeadlessApplicationConfiguration());

        final Lwjgl3ApplicationConfiguration windowConfig = new Lwjgl3ApplicationConfiguration();
        windowConfig.setWindowedMode(854, 480);
        windowConfig.setInitialVisible(false);
        windowConfig.setTitle("Test");

        this.window = new Lwjgl3Window(applicationListener, windowConfig, this);
        final long windowHandle = createGlfwWindow(windowConfig, 0);
        window.create(windowHandle);
        window.makeCurrent();

        Gdx.input = new DefaultLwjgl3Input(window);
    }

    public void render() {
        boolean shouldRequestRendering;

        synchronized (runnables) {
            shouldRequestRendering = runnables.size > 0;
            executedRunnables.clear();
            executedRunnables.addAll(runnables);
            runnables.clear();
        }

        for (final Runnable runnable : new Array.ArrayIterator<>(executedRunnables)) {
            runnable.run();
        }

        if (shouldRequestRendering && !window.getGraphics().isContinuousRendering())
            window.requestRendering();
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return applicationListener;
    }

    @Override
    public Graphics getGraphics() {
        return window.getGraphics();
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public Input getInput() {
        return window.getInput();
    }

    @Override
    public Files getFiles() {
        return files;
    }

    @Override
    public Net getNet() {
        return net;
    }

    @Override
    public void log(String tag, String message) {
        if (logLevel >= LOG_INFO) getApplicationLogger().log(tag, message);
    }

    @Override
    public void log(String tag, String message, Throwable exception) {
        if (logLevel >= LOG_INFO) getApplicationLogger().log(tag, message, exception);
    }

    @Override
    public void error(String tag, String message) {
        if (logLevel >= LOG_ERROR) getApplicationLogger().error(tag, message);
    }

    @Override
    public void error(String tag, String message, Throwable exception) {
        if (logLevel >= LOG_ERROR) getApplicationLogger().error(tag, message, exception);
    }

    @Override
    public void debug(String tag, String message) {
        if (logLevel >= LOG_DEBUG) getApplicationLogger().debug(tag, message);
    }

    @Override
    public void debug(String tag, String message, Throwable exception) {
        if (logLevel >= LOG_DEBUG) getApplicationLogger().debug(tag, message, exception);
    }

    @Override
    public int getLogLevel() {
        return logLevel;
    }

    @Override
    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public ApplicationLogger getApplicationLogger() {
        return applicationLogger;
    }

    @Override
    public void setApplicationLogger(ApplicationLogger applicationLogger) {
        this.applicationLogger = applicationLogger;
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.HeadlessDesktop;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getNativeHeap() {
        return getJavaHeap();
    }

    @Override
    public Preferences getPreferences(String name) {
        if (!preferences.containsKey(name))
            preferences.put(name, new HeadlessPreferences(name, ".prefs/"));

        return preferences.get(name);
    }

    @Override
    public Clipboard getClipboard() {
        return null;
    }

    @Override
    public void postRunnable(Runnable runnable) {
        synchronized (runnables) {
            runnables.add(runnable);
        }
    }

    @Override
    public void exit() {
        synchronized (lifecycleListeners) {
            for (final LifecycleListener lifecycleListener : new Array.ArrayIterator<>(lifecycleListeners)) {
                lifecycleListener.pause();
                lifecycleListener.dispose();
            }
        }

        window.dispose();

        glfwTerminate();

        running = false;
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        synchronized (lifecycleListeners) {
            lifecycleListeners.add(listener);
        }
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        synchronized (lifecycleListeners) {
            lifecycleListeners.removeValue(listener, true);
        }
    }

    @Override
    public Lwjgl3Audio createAudio(Lwjgl3ApplicationConfiguration config) {
        return null;
    }

    @Override
    public Lwjgl3Input createInput(Lwjgl3Window window) {
        return null;
    }
}
