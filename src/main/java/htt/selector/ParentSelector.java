package htt.selector;

import java.util.List;

public interface ParentSelector {
    int[] select(List<int[]> population, List<Integer> fitness);
}
