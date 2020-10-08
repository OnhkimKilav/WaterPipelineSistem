package ua.mikhno.DBbest.service;

import ua.mikhno.DBbest.model.Pipeline;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CSVReaderWriter {
    private String pathFileResult = "src/main/Result.csv";
    private String pathFilePipeline = "src/main/Pipeline.csv";
    private String pathFilePipelineSet = "src/main/PipelineSet.csv";

    public void writeResult(List<String> results) {
        String lineSeparator = System.getProperty("line.separator");

        try (FileWriter fileWriter = new FileWriter(pathFileResult)) {
            fileWriter.append("ROUTE EXISTS,MIN LENGTH" + lineSeparator);

            for (String result : results) {
                fileWriter.append(result);
                fileWriter.append(lineSeparator);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pipeline> readPipeline() {
        List<Pipeline> pipelines = new LinkedList<>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(pathFilePipeline))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length > 0) {
                    Pipeline pipeline = new Pipeline();
                    pipeline.setStart(Integer.parseInt(values[0]));
                    pipeline.setEndPoint(Integer.parseInt(values[1]));
                    pipeline.setLength(Integer.parseInt(values[2]));
                    pipelines.add(pipeline);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pipelines;
    }

    public LinkedList<String> readPipelineSet() {
        LinkedList<String> pipelinesSet = new LinkedList<>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(pathFilePipelineSet))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                pipelinesSet.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pipelinesSet;
    }

    public void writeInformationForUserCSV() {
        System.out.println("Water pipeline system");
        for (Pipeline pipeline : readPipeline()) {
            System.out.println("Pipeline start: " + pipeline.getStart() +
                    " ,end: " + pipeline.getEndPoint() + " ,length: " + pipeline.getLength());
        }
        System.out.println();
        System.out.println("Set of points");
        for (String s : readPipelineSet()) {
            String[] values = s.split(",");
            System.out.println("First dot: " + values[0] + " ,second: " + values[1]);
        }
    }
}
