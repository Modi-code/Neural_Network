import java.util.Arrays;

public class Network {
    private final int[] Network_Layer_Sizes;
    private final int Network_Size;
    private final int Input_Size;
    private final int Output_Size;

    public double[][] output;
    public double[][][] weight;
    public double[][] bias;

    public double[][] output_derivative;
    public double[][] error;


    public Network(int... Network_Layer_Sizes) {
        this.Network_Layer_Sizes = Network_Layer_Sizes;
        this.Network_Size = this.Network_Layer_Sizes.length;
        this.Input_Size = this.Network_Layer_Sizes[0];
        this.Output_Size = this.Network_Layer_Sizes[Network_Size-1];

        this.bias = new double[Network_Size][];
        this.weight = new double[Network_Size][][];
        this.output = new double[Network_Size][];

        this.output_derivative = new double[Network_Size][];
        this.error = new double[Network_Size][];

        for (int Layer = 0; Layer < Network_Size; Layer++) {
            bias[Layer] = new double[this.Network_Layer_Sizes[Layer]];
            output[Layer] = new double[this.Network_Layer_Sizes[Layer]];
            output_derivative[Layer] = new double[this.Network_Layer_Sizes[Layer]];
            error[Layer] = new double[this.Network_Layer_Sizes[Layer]];
            for (int Neuron = 0; Neuron < this.Network_Layer_Sizes[Layer]; Neuron++) {
                bias[Layer][Neuron] = NetworkTools.generateNumber(0.3, 0.7);
                if(Layer>0){
                    weight[Layer] = new double[this.Network_Layer_Sizes[Layer]][this.Network_Layer_Sizes[Layer - 1]];
                    for (int PrevNeuron = 0; PrevNeuron < this.Network_Layer_Sizes[Layer - 1]; PrevNeuron++) {
                        weight[Layer][Neuron][PrevNeuron] = NetworkTools.generateNumber(0.3, 0.7);
                    }
                }
            }
        }
    }

    public void calculate(double[] picInput){
        if(picInput.length!=Input_Size){return;}
        output[0] = picInput;
        for(int Layer=1;Layer<Network_Size;Layer++){
            for(int Neuron=0;Neuron<Network_Layer_Sizes[Layer];Neuron++){
                double sum=bias[Layer][Neuron];
                for (int PrevNeuron = 0; PrevNeuron < Network_Layer_Sizes[Layer - 1]; PrevNeuron++) {
                    sum+=weight[Layer][Neuron][PrevNeuron]*output[Layer-1][PrevNeuron];
                    output_derivative[Layer][Neuron]=output[Layer][Neuron]*(1-output[Layer][Neuron]);
                }
                output[Layer][Neuron]=sigmoid(sum);
            }
        }
    }

    public void predict(double[] input){
        calculate(input);
        System.out.println("Initial guess: "+Arrays.toString(output[Network_Size-1]));
    }

    public void backPropagate(double[] target){
        for(int Layer=Network_Size-1;Layer>0;Layer--){
            for(int Neuron=0;Neuron<Network_Layer_Sizes[Layer];Neuron++){
                double sum =0;
                if(Layer==Network_Size-1){
                    error[Layer][Neuron]=output_derivative[Layer][Neuron]*
                            (output[Layer][Neuron]-target[Neuron]);
                }else{
                    for(int NextNeuron=0;NextNeuron<Network_Layer_Sizes[Layer+1];NextNeuron++){
                        sum+=error[Layer+1][NextNeuron]*weight[Layer+1][NextNeuron][Neuron];
                    }
                    error[Layer][Neuron]=sum*output_derivative[Layer][Neuron];
                }
            }
        }
    }

    public void updateWeight(double eta){
        for(int Layer=1;Layer<Network_Size;Layer++){
            for(int Neuron=0;Neuron<Network_Layer_Sizes[Layer];Neuron++){
                for (int PrevNeuron = 0; PrevNeuron < Network_Layer_Sizes[Layer - 1]; PrevNeuron++){
                    weight[Layer][Neuron][PrevNeuron]+= -eta *error[Layer][Neuron]*output[Layer-1][PrevNeuron];
                }
                bias[Layer][Neuron]=-eta* error[Layer][Neuron];
            }
        }
    }

    public void train(double[] input,double eta,double[] target){
        if(input.length!=output[0].length){return;}
        calculate(input);
        backPropagate(target);
        updateWeight(eta);
    }

    public double sigmoid(double x){return (double)1/(1+Math.exp(-x));}

    public static void main(String[] args) {
        double [][] input=new double[5][5];
        for(int i=0;i< input.length;i++){
            for(int j=0;j< input[0].length;j++){
                input[i][j]=Math.random();
            }
        }
        double[] picInput=NetworkTools.input1D(input);

        double [] target={0.1, 0.2, 0.5, 0.5, 0.6, 0.8, 0.6, 0.3, 0.6, 0.7};
        Network net=new Network(25,20,10);

        net.predict(picInput);

        for(int i=0;i<1000;i++){
            net.train(picInput,0.1,target);
        }
        net.calculate(picInput);

        System.out.println(Arrays.toString(net.output[net.Network_Size-1]));
    }
}

