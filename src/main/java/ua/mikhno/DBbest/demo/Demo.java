package ua.mikhno.DBbest.demo;

import ua.mikhno.DBbest.service.CSVReaderWriter;
import ua.mikhno.DBbest.service.PipelineService;
import ua.mikhno.DBbest.transaction.PipelineTransaction;

public class Demo {

    public static void main(String args[]) {
        PipelineService pipelineService = new PipelineService();
        PipelineTransaction pipelineTransaction = new PipelineTransaction();
        CSVReaderWriter csvReaderWriter = new CSVReaderWriter();

        System.out.println("Hello this program calculate the minimal route length\n");
        csvReaderWriter.writeInformationForUserCSV();

        pipelineTransaction.transaction();
        pipelineService.workWithService();

        pipelineService.writeInformationForUserService();
    }


}
