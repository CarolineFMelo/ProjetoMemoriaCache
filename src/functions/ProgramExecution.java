package functions;
import java.util.Scanner;

import functions.Associative.*;
import functions.Direct.DirectExecution;
import functions.setAssociative.*;

public class ProgramExecution {

    public static void main(String[] args) {
    	Scanner question = new Scanner(System.in);
    	
    	System.out.println("Choose one of the methods: [1] Direct / [2] Associative / [3] Set Associative");
    	int method = question.nextInt();
    	
    	switch (method) {
	    	case 1:
	    		DirectExecution direct = new DirectExecution();
	        	direct.readingDataDirect();
	        	break;
	    	case 2:
	    		System.out.println("Choose one of the algorithms: [1] LFU / [2] LRU / [3] FIFO / [4] Random");
	    		int algorithm = question.nextInt();
	    		question.close();
	    		switch (algorithm) {
	    			case 1:
	    				Lfu lfuAlgorithm = new Lfu();
	    				lfuAlgorithm.runLfu();
	    				break;
	    			case 2:
	    				Lru lruAlgorithm = new Lru();
	    				lruAlgorithm.runLru();
	    				break;
	    			case 3:
	    				Fifo fifoAlgorithm = new Fifo();
	    				fifoAlgorithm.runFifo();
	    				break;
	    			case 4:
	    				Random randomAlgorithm = new Random();
	    				randomAlgorithm.runRandom();
	    				break;
	    		}
	    		break;
	    	case 3:
	    		System.out.println("Choose one of the algorithms: [1] LFU / [2] LRU / [3] FIFO / [4] Random");
	    		int algorithmSA = question.nextInt();
	    		question.close();
	    		switch (algorithmSA) {
    			case 1:
    				LfuSA lfu = new LfuSA();
    	    		lfu.runLfuSA();
    	    		break;
    			case 2:
    				LruSA lru = new LruSA();
    				lru.runLruSA();
    				break;
    			case 3:
    				FifoSA fifo = new FifoSA();
    				fifo.runFifoSA();
    				break;
    			case 4:
    				RandomSA random = new RandomSA();
    				random.runRandomSA();
    				break;
    		}
    		break;
    	}   	
    }

}
