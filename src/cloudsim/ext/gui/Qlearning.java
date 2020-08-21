package cloudsim.ext.gui;
import java.text.DecimalFormat;
import java.util.Random;
public class Qlearning {
    final DecimalFormat df = new DecimalFormat("#.##");
    final double alpha = 0.1;
    final double gamma = 0.9;
    final int state1  = 0;
    final int state2 = 1;
    final int state3 = 2;
    final int state4 = 3;
    final int state5 = 4;
    final int state6 = 5;
    final int statesCount = 10;
    final int[] states = new int[]{state1 ,state2,state3,state4,state5,state6};
    int[][] R = new int[statesCount][statesCount];
    double[][] Q = new double[statesCount][statesCount]; 
   int[] actionsFromFOG1 = new int[] { state2, state4 };
    int[] actionsFromFOG2 = new int[] { state1 , state3, state5 };
    int[] actionsFromFOG3 = new int[] { state3 };
    int[] actionsFromFOG4 = new int[] { state1 , state5 };
    int[] actionsFromFOG5 = new int[] { state2, state4, state6 };
    int[] actionsFromFOG6  = new int[] { state3, state5 };
    int[] actionsFromFOG7 = new int[] { state2, state4 };
    int[] actionsFromFOG8 = new int[] { state1 , state3, state5 };
    int[] actionsFromFOG9 = new int[] { state3 };
    int[] actionsFromFOG10 = new int[] { state1 , state5 };
    int[][] actions = new int[][] { actionsFromFOG1, actionsFromFOG2, actionsFromFOG3,
            actionsFromFOG4, actionsFromFOG5, actionsFromFOG6, actionsFromFOG7,
            actionsFromFOG8, actionsFromFOG9, actionsFromFOG10 }; 
    String[] stateNames = new String[] { "FOG1", "FOG2", "FOG3", "FOG4", "FOG5", "FOG6", "FOG7", "FOG8", "FOG9", "FOG10" };
     public Qlearning() {
        init();
    }
     public void init() {        
        R[state2][state3] = 100; 
        R[state6][state3] = 100; 
    }
     void run() {        
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int state = rand.nextInt(statesCount);
            while (state != state3)
            {                
                int[] actionsFromState = actions[state];
                int index = rand.nextInt(actionsFromState.length);
                int action = actionsFromState[index];
                int nextState = action; 
                double q = Q(state, action);
                double maxQ = maxQ(nextState);
                int r = R(state, action); 
                double value = q + alpha * (r + gamma * maxQ - q);
                setQ(state, action, value);
                state = nextState;
            }
        }
    }
     double maxQ(int s) {
        int[] actionsFromState = actions[s];
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < actionsFromState.length; i++) {
            int nextState = actionsFromState[i];
            double value = Q[s][nextState];
 
            if (value > maxValue)
                maxValue = value;
        }
        return maxValue;
    }     
    int policy(int state) {
        int[] actionsFromState = actions[state];
        double maxValue = Double.MIN_VALUE;
        int policyGotoState = state; 
        for (int i = 0; i < actionsFromState.length; i++) {
            int nextState = actionsFromState[i];
            double value = Q[state][nextState];
 
            if (value > maxValue) {
                maxValue = value;
                policyGotoState = nextState;
            }
        }
        return policyGotoState;
    }
     double Q(int s, int a) {
        return Q[s][a];
    } 
    void setQ(int energy, int time, double load) {
        Q[energy][time] = load;
    } 
    int R(int s, int a) {
        return R[s][a];
    } 
    void printResult() {
        System.out.println("Print result");
        for (int i = 0; i < Q.length; i++) {
            System.out.print("out from " + stateNames[i] + ":  ");
            for (int j = 0; j < Q[i].length; j++) {
                System.out.print(df.format(Q[i][j]) + " ");
            }
            System.out.println();
        }
    }  
    void showPolicy() {
        System.out.println("\nshowPolicy");
        for (int i = 0; i < states.length; i++) {
            int from = states[i];
            int to =  policy(from);
            System.out.println("from "+stateNames[from]+" goto "+stateNames[to]);
        }           
    }
}
