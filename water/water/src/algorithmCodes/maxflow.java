package algorithmCodes;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.min;

/**
 * Created by User on 22-May-17.
 */
public class maxflow {


    boolean bfs(int rGraph[][], int s, int t, int parent[] , int V)
    {
        boolean visited[] =  new boolean[V];
        for(int i=0;i<visited.length; i++) visited[i]=false;

        Queue< Integer> q = new LinkedList<>();;
        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (q.isEmpty()==false)
        {
            int u = q.peek();
            q.remove();

            for (int v=0; v<V; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return (visited[t] == true);
    }

    int fordFulkerson(int graph[][], int s, int t , int V)
    {
        int u, v;

        int rGraph[][] = new int[V][V];
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int parent[] = new int[V];

        int max_flow = 0;


        while (bfs(rGraph, s, t, parent , V))
        {

            int path_flow = 10000000;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = min(path_flow, rGraph[u][v]);
            }

            for (v=t; v != s; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }

        return max_flow;
    }

}
