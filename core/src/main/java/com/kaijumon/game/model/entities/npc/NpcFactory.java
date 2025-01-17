package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.model.IWorldModel;
import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Trainer;
import com.kaijumon.game.model.entities.TrainerType;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

public class NpcFactory {

    IWorldModel model;
    Point mapDimensions;

    public NpcFactory(IWorldModel model) {
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
         ITrainer trainer = new Trainer(Arrays.asList(model.getRandomKaijumon()), TrainerType.NPC);
        return new Npc(x, y, new MoveBehaviourRandom(model, a, b), dialogId, 1, model.getPlayer(), trainer);
     }

    /**
     * Creates a new Npc with a patrol behaviour or a still behaviour if the list only contains one point
     * @param pointList list of points the npc should patrol, only need to add points at the corners of the patrol area
     * @param dialogId the id of the dialog the npc should have. If the id is 0 the npc will not have a dialog
     * @return a new Npc
     */
     public Npc getNpc(List<Point> pointList, int dialogId){
        if (pointList == null) {
            throw new IllegalArgumentException("pointList can not be null");
        }
         ITrainer trainer = new Trainer(Arrays.asList(model.getRandomKaijumon()), TrainerType.NPC);

        if (pointList.size() == 1) return new Npc(pointList.get(0).x, pointList.get(0).y, new MoveBehaviourStill(), dialogId, 1, model.getPlayer(), trainer);
        return new Npc(pointList.get(0).x, pointList.get(0).y, new MoveBehaviourPatrol(model, pointList), dialogId, 1, model.getPlayer(), trainer);
     }
}
