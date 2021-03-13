# :ant: Lem-in V2

Imagine you have to transfer N ants from A to B on an Ant Farm (a graph) in the fastest possible way.

This program finds the best set of paths on a graph and does all the work!

## The rules

* You have a bunch of ants and a graph
* Graph contains rooms (vertices) with unique names and links (edges) between these rooms
* You can move as many ants as you want per step
* You can't move an ant twice per step
* Room can contain at most one ant at a time (except `start` and `end`)

## How to use?

The program tries to build a graph from user input and finds the best set of paths for transfer.

Lets describe a simple graph:

<p align="center">
  <img alt="graph" width="600px" src="/assets/graph.png" />
</p>

On the first line, you have to specify ants count:
```
100
```

Then, the program expects to read a list of rooms:
```
green 0 0
purple 0 0
deadEnd 0 0
blue 0 0
red 0 0
```

Two of them have to be marked as `start` and `end` rooms:
```
##start
start 0 0

##end
end 0 0
```

Finally, link existing rooms between each other:
```
start-green
start-purple
start-deadEnd
start-blue
blue-red
red-end
green-purple
purple-end
green-end
```

<details>
  <summary>Here is a complete example.</summary>
  
  ```
  # ants count
  100
  # rooms
  green 0 0
  purple 0 0
  deadEnd 0 0
  blue 0 0
  red 0 0
  ##start
  start 0 0
  ##end
  end 0 0
  # links
  start-green
  start-purple
  start-deadEnd
  start-blue
  blue-red
  red-end
  green-purple
  purple-end
  green-end
  ```
  
</details>

As a result, **Lem-in V2** shows a list of some considered paths and the best set (with steps count - `e`):
```
Found paths (5):
path_0 (3) [start, green, end]
path_1 (3) [start, purple, end]
path_2 (4) [start, blue, red, end]
path_3 (4) [start, green, purple, end]
path_4 (4) [start, purple, green, end]

Best set (s = 3, e = 35):
path_0 (3) [start, green, end]
path_1 (3) [start, purple, end]
path_2 (4) [start, blue, red, end]
```

## How to run?

0. Ensure you have installed Java.
1. Clone: `git clone https://github.com/ollevche/lem-in-v2 && cd lem-in-v2`
2. Build via gradle wrapper: `./gradlew`
3. Run jar file: `java -jar build/libs/lemin-2.0.jar`
4. Get rid of this annoying problem with pathfinding when transferring ants on a graph!

## About

This project is a part of the **42 School's** Algorithmic branch.

Originally, it has been implemented in **C** ([here is the repo](https://github.com/ollevche/lem-in)), this is **Java** version with a faster algorithm.
