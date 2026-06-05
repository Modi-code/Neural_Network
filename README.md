# Matrix Neural Network from Scratch (Java)

A lightweight, high-performance feedforward neural network built entirely from scratch in raw Java using multidimensional arrays. This project implements full forward propagation, backpropagation, and weight/bias optimization using gradient descent without relying on external ML frameworks.

##  Key Architectural Features
* **Zero Dependencies:** Built entirely with standard Java libraries.
* **Dynamic Layer Sizing:** The network architecture sizing is fully customizable during initialization using a standard constructor (new Network(25, 20, 10)).

## Structural Architecture

* **Input Layer:** Takes flattened multi-dimensional feature vectors (e.g., a flattened $5 \times 5$ pixel grid into 25 inputs).
* **Hidden Layers:** Configurable hidden neuron arrays processing abstract feature representations.
* **Output (Top) Layer:** Generates continuous target prediction vectors mapping to exact classification scores via optimized mathematical convergence.

## How to Run Locally
```bash
