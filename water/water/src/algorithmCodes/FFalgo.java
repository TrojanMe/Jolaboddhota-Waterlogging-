package algorithmCodes;
import java.util.LinkedList;
/**
 * Created by User on 09-Jul-17.
 */


public class FFalgo
{
    int V;

    boolean bfs(float rGraph[][], int s, int t, int parent[])
    {

        boolean visited[] = new boolean[1000];
        for(int i=0; i<1000; ++i)
            visited[i]=false;


        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;


        while (queue.size()!=0)
        {
            int u = queue.poll();

            for (int v=0; v<V; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }


        return (visited[t] == true);
    }

    int fordFulkerson(float graph[][], int s, int t)
    {
        int u, v;


        float rGraph[][] = new float[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];


        int parent[] = new int[V];

        int max_flow = 0;

        while (bfs(rGraph, s, t, parent))
        {

            float path_flow = Integer.MAX_VALUE;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
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

    public void generatePipeline(float[][] graph , int v)
    {
        V = v+2;

        /*for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                System.out.print(graph[i][j] + "  ");
            }
            System.out.println();
        }*/

        System.out.println("The maximum possible flow is " + this.fordFulkerson(graph, 0, v+1));

    }
}