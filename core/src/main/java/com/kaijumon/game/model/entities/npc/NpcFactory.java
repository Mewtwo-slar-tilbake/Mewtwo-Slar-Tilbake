package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.model.Model;

import java.awt.Point;
import java.util.List;

public class NpcFactory {

    Model model;
    Point mapDimensions;

    public NpcFactory(Model model) {
        this.model = model;
        this.mapDimensions = model.getMapDimensions();
    }

    /**
     * Creates a new Npc with the random behaviour
     * @param a one corner of the area the npc should patrol
     * @param b the other corner of the area the npc should patrol
     * @param dialogId the id of the dialog the npc should have. If the id is 0 the npc will not have a dialog
     * @return a new Npc
     */
     public Npc getNpc(Point a, Point b, int dialogId){
        int x;
        int y;
        Point possiblePoint = new Point();
        int checkIfInfiniteLoop = 0;
         do {
             x = (int) (Math.random() * (b.x - a.x) + a.x);
             y = (int) (Math.random() * (b.y - a.y) + a.y);
             possiblePoint.setLocation(x, y);
             checkIfInfiniteLoop++;
         } while (model.isTileBlocked(possiblePoint));
         if (checkIfInfiniteLoop > 100) throw new IllegalStateException("Could not find a valid point for the npc");
        return new Npc(x, y, new MoveBehaviourRandom(model, a, b), dialogId, 1);
     }

    /**
     * Creates a new Npc with a patrol behaviour or a still behaviour if the list only contains one point
     * @param pointList list of points the npc should patrol, only need to add points at the corners of the patrol area
     * @param dialogId the id of the dialog the npc should have. If the id is 0 the npc will not have a dialog
     * @return a new Npc
     */
     public Npc getNpc(List<Point> pointList, int dialogId){
        if (pointList == null) throw new IllegalArgumentException("pointList can not be null");
        if (pointList.size() == 1) return new Npc(pointList.get(0).x, pointList.get(0).y, new MoveBehaviourStill(), dialogId, 1);
        return new Npc(pointList.get(0).x, pointList.get(0).y, new MoveBehaviourPatrol(model, pointList), dialogId, 1);
     }
}
