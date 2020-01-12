# http://www.usaco.org/index.php?page=viewproblem2&cpid=842

import math

class Graph:
    def __init__(self):
        self.graph = {}
        self.distances = {}

    # Expects destinations as an iterable
    def set_paths(self, start, destinations):
        if start not in self.graph:
            self.graph[start] = destinations
        else: self.graph[start] += destinations

        for node in destinations:
            if node not in self.graph:
                self.graph[node] = []
            # Because each node is bidirectional, a path from start --> dest means there is a path from dest --> start
            self.graph[node] += [start]

    def is_connection(self, node_a, node_b, visited):
        if node_a == node_b:
            return True
        # elif node_a in visited:
        #    return False
        elif node_b in self.graph[node_a]:
            return True

        visited.append(node_a)
        for node in self.graph[node_a]:
            if node not in visited and self.is_connection(node, node_b, visited):
                return True
        return False


    def is_connected(self):
        '''
        1. Choose an arbitrary node x of the graph G as the starting point
        2. Determine the set A of all the nodes which can be reached from x.
        3. If A is equal to the set of nodes of G, the graph is connected; otherwise it is disconnected.
        '''
        keys = list(self.graph.keys())
        arbitrary = keys[0]
        for node in keys:
            if not self.is_connection(node, arbitrary, []):
                return False
        return True

    def __str__(self):
        return str(self.graph)


    def set_dist(self, node_a, node_b, distance):
        self.distances[(node_a, node_b)] = self.distances[(node_b, node_a)] = distance

    def get_dist(self, node_a, node_b):
        return self.distance[(node_a, node_b)]

    # Uses Dijkastra's shortest path
    def shortest_path(self):
        shortest_from_a = {}
        keys = list(self.graph.keys())
        node_a = keys[0]
        for node in keys: # Distance from a to all nodes is infinite
            shortest_from_a[node] = math.inf
        shortest_from_a[node_a] = 0
        self.shortest_path_(node_a, shortest_from_a, 0, [], keys)
        return shortest_from_a

    def shortest_path_(self, focus, shortest_from_a, dist, visited, unvisited, previous):

        for node in self.graph[focus]:
            if node not in visited:
                # If new shortest distance found, update it
                if self.distances[node, focus] + dist < shortest_from_a[node]:
                    shortest_from_a[node] = self.distances[node, focus] + dist
                    previous[node] = focus # Helps trace back path of shortest distances

        visited += [unvisited.remove(focus)]


        if unvisited == []:
            return

        smallest = math.inf

        # Find node connected to item with smallest known distance from a, and visit it
        for item in unvisited:
            if shortest_from_a[item] < smallest:
                next = item

        self.shortest_path_(next, shortest_from_a, shortest_from_a[next], visited, unvisited, previous)

    def remove_connection(self, node_a, node_b):
        self.graph[node_a].remove(node_b)
        self.graph[node_b].remove(node_a)
        #del self.distances[node_a, node_b], self.distances[node_b, node_a]



fr = open('Disrupt.txt', 'r')
txt = fr.read().split('\n')
fr.close()

farm = Graph()
N , M = txt[0].split(' ')
N, M = int(N), int(M)

blocked = []
for i in range(1, N ):
    p, q = txt[i].split(' ')
    p, q = int(p), int(q)
    blocked.append([p, q])
    farm.set_paths(p, [q])

additional_paths = []
for i in range(N, N + M):
    p, q, r = txt[i].split(' ')
    p, q, r = int(p), int(q), int(r)
    additional_paths.append( [p, q, r] )


from copy import deepcopy
from operator import itemgetter
additional_paths.sort(key=itemgetter(2)) # Sort by r

for path in blocked:
    smallest = -1
    temp = deepcopy(farm)
    temp.remove_connection(path[0], path[1])
    for additional_path in additional_paths:
        #print(additional_path)
        temp.set_paths(additional_path[0], [additional_path[1]] )
        if temp.is_connected():
            print(additional_path[2])
            break
        temp.remove_connection(additional_path[0], additional_path[1])