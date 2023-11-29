package sharedObject;

import interfaces.IRenderable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
    private List<IRenderable> entities;
    private Comparator<IRenderable> comparator;
    private static final RenderableHolder instance = new RenderableHolder();

    // constructor
    public RenderableHolder() {
        entities = new ArrayList<IRenderable>();
        comparator = (IRenderable o1, IRenderable o2) -> {
            if (o1.getZ() > o2.getZ()) return 1;
            return -1;
        };
    }

    // methods
    public void add(IRenderable entity) {
        entities.add(entity);
        // Sort our list by Z
        Collections.sort(entities, comparator);
    }

    public static RenderableHolder getInstance() {
        return instance;
    }

    public List<IRenderable> getEntities() {
        return entities;
    }


}
