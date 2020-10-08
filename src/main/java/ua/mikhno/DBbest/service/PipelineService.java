package ua.mikhno.DBbest.service;

import ua.mikhno.DBbest.model.Pipeline;
import ua.mikhno.DBbest.transaction.PipelineTransaction;

import java.util.*;

public class PipelineService {
    public List<String> result = new ArrayList<>();
    private CSVReaderWriter csvReaderWriter = new CSVReaderWriter();

    public void workWithService() {
        int v = countVertex() + 1;
        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }

        List<Pipeline> pipelines = csvReaderWriter.readPipeline();
        for (Pipeline pipeline : pipelines) {
            addEdge(adj, pipeline.getStart(), pipeline.getEndPoint());
        }

        LinkedList<String> pipelinesSet = csvReaderWriter.readPipelineSet();
        for (String s : pipelinesSet) {
            String[] str = s.split(",");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            printShortestDistance(adj, start, end, v, pipelines);
        }

        csvReaderWriter.writeResult(result);
    }

    private int countVertex() {
        int v = 0;
        List<Pipeline> pipelines = csvReaderWriter.readPipeline();
        for (Pipeline pipeline : pipelines) {
            if (pipeline.getStart() > v)
                v = pipeline.getStart();
            if (pipeline.getEndPoint() > v)
                v = pipeline.getEndPoint();
        }
        return v;
    }

    public void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    public void printShortestDistance(
            ArrayList<ArrayList<Integer>> adj,
            int s, int dest, int v, List<Pipeline> pipelines) {

        int pred[] = new int[v];
        int dist[] = new int[v];

        if (BFS(adj, s, dest, v, pred, dist) == false) {
            result.add("FALSE");
            return;
        }

        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        int length = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            for (Pipeline pipeline : pipelines) {
                if (pipeline.getStart() == path.get(i) &&
                        pipeline.getEndPoint() == path.get(i - 1)) {
                    length += pipeline.getLength();
                }
            }
        }
        result.add("TRUE," + length);
    }

    private boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                        int dest, int v, int pred[], int dist[]) {

        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean visited[] = new boolean[v];

        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }

    public void writeInformationForUserService() {
        System.out.println("\nResult");
        for (String s : result) {
            System.out.println("First dot: " + s);
        }
    }
}
