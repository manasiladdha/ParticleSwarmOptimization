# ParticleSwarmOptimzation
A particle swarm optimization algorithm implementation with simultaneous pickup and drop for medicines distribution management.


# Problem Statement
The distribution of medicines from pharmaceutical company to medical stores involves delivery of new medicines along with collection of expired medicines from the stores. This distribution is one of the key activities performed by these pharmaceutical companies and plays an important role in the effectiveness of business. Since the company have limited number of vehicles for delivery and pickup, the challenge involved in this distribution system is to minimize the total travel distance of the route. Since each vehicle will be delivering new medicines and will be collecting expired medicines on the same vehicle and has a limited capacity, there is another challenge to maximize the profits on each route. Delivering new medicines contribute to profit while collecting expired medicines does not. 


# Proposed Solution

Medicine Distribution Problem with simultaneously pick up and drop off can be categorized as SCVRP-SPD i.e. Symmetric Capacitated Vehicle Routing Problem with Simultaneous Pickup and Drop.  I have implemented Particle Swarm Optimization (PSO) algorithm to find the optimal route with minimal travel distance along with vehicle capacity as a constraint. The target is to identify a single route between S stores from 1 depot so that K vehicles with L capacity can be utilized to maximize the profits and reduce transportation costs under given constraints. 

# What is PSO and Why PSO?

PSO is an evolutionary computation technique that is based on population-based swarm optimization. 
•	It is an iterative algorithm that engages several simple entities—particles—iteratively over the search space of objective functions.  
•	The particles evaluate their fitness values, with respect to the search function, at their current locations. Subsequently, each particle determines its movement through the search space by combining information about its current fitness, its best fitness from previous locations (individual perspective) and best fitness locations with regards to one or more members of the swarm (social perspective), with some random perturbations. 
•	The next iteration starts after the positions of all particles have been updated.

Since Medicine Distribution Management is a NP-Hard Problem, it is difficult to find a best solution in a required time. We need an optimization algorithm that can identify an optimal solution in an acceptable time frame. I have implemented continuous PSO variant in this problem. Continuous PSO works well with the small-size benchmark problems (the number of stores is less than 75).
