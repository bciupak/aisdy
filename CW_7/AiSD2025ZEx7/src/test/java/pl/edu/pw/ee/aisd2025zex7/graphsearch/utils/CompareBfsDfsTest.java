package pl.edu.pw.ee.aisd2025zex7.graphsearch.utils;

import org.junit.jupiter.api.Test;

import pl.edu.pw.ee.aisd2025zex7.graphsearch.bfs.BfsMazeTxtWayOutSearcher;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.dfs.DfsMazeTxtWayOutSearcher;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.PATH_MAZE_21_21;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.PATH_MAZE_201_201;

public class CompareBfsDfsTest {

    @Test
    public void compareBfsAndDfsOnMazes() {
        BfsMazeTxtWayOutSearcher bfs = new BfsMazeTxtWayOutSearcher();
        DfsMazeTxtWayOutSearcher dfs = new DfsMazeTxtWayOutSearcher();

        System.out.println("BFS 21x21: " + bfs.findWayOutOfMaze(PATH_MAZE_21_21, 1, 0));
        System.out.println("DFS 21x21: " + dfs.findWayOutOfMaze(PATH_MAZE_21_21, 1, 0));

        System.out.println("BFS 201x201: " + bfs.findWayOutOfMaze(PATH_MAZE_201_201, 1, 0));
        System.out.println("DFS 201x201: " + dfs.findWayOutOfMaze(PATH_MAZE_201_201, 1, 0));
    }

}
