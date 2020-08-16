# Collusion-group-detector
FPY
Firstly, the distance is the trading connection between traders thus we can use the trading volume instead of distance.

Secondly, the prediction is not a single point but a collusion group contains many points. Thus the prediction process needs to be changed. We will traverse the data and arrange the volumes first, once we find a point, we will not only label that point but also label the k-nearest points. In this step we may make wrong prediction. 

Finally we need to check all of the predictions. We will delete wrong predictions and divide points into different collusion groups. 

At the beginning, we need to check each pair of the sellers by three conditions:

For seller1 and seller2:

condition1: Whether they have more than kt same buyers.
condition2: Whether seller1 is in the seller2’s buyer list
condition3: Whether seller2 is in the seller1’s buyer list

If seller1 and seller2 satisfy all of the condition, then the seller1 and seller2 are suspect collusion groups. Then we will check seller1 and seller3 until all of the sellers have been checked. When it is finished, we can get a suspect collusion groups list.

Change the value of k and kt, do the step2 to step 4 again. Then we can get another suspect collusion groups list. If some sellers are always appear in the list. (Like more than X times) we will say that this seller is a collusion group.
