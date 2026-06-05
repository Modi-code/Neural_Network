# Matrix Neural Network from Scratch (Java)

A lightweight, high-performance feedforward neural network built entirely from scratch in raw Java using multidimensional arrays. This project implements full forward propagation, backpropagation, and weight/bias optimization using gradient descent without relying on external ML frameworks.

##  Key Architectural Features
* **Zero Dependencies:** Built entirely with standard Java libraries.
* **Dynamic Layer Sizing:** The network architecture sizing is fully customizable during initialization using a standard constructor (new Network(25, 20, 10)).

## Structural Architecture

Input Layer: Flattens a $5 \times 5$ pixel grid into a 25-element single-dimension array.
Hidden Layers: Processes structural features and patterns using custom, fully connected neuron arrays.
Output Layer: Outputs a 10-element prediction vector matching the network's final targeted scores

## How to Run Locally
```bash
git clone https://github.com/Modi-code/Neural_Network.git
cd Neural_Network
