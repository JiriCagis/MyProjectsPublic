<h1>Decision tree </h1>
Algorithm analysis table contains information about weather and create rules on based this values.

<pre>
                                          ____________
                                          -   ROOT   -
                                          -          -
                                          ------------
                                      *         *       *
                                   *            *            *
                                *               *                * 
                     _________*__          _____*______         ____*_______
                     -  SUNNY   -          - OVERCAST -         -  RAINY   - 
                     -          -          -          -         -          -
                     ------------          ------------         ------------
                 *         *       *
               *           *           *
        _____*__       ____*___      _____*__
        - HIGH -       -NORMAL-      - LOW  -
        --------       --------      --------
 </pre>

<h3> What is Decision tree? </h3>
A decision tree is a decision a tool that uses a tree-like graph or model of decisions and their possible consequences, including chance event outcomes, resource costs, and utility. It is one way to display an algorithm.
Decision trees are commonly used in operations research, specifically in decision analysis, to help identify a strategy most likely to reach a goal. <a href="https://en.wikipedia.org/wiki/Decision_tree">Wiki page</a>

<h3> Describe our algorithm </h3>
Algorithm represent n-arity decision tree for vocation rules in table about weather.
On top tree is partition by attribute "Outlook", in another floor divide by attribute 
"Humidity", another floor divide by attribute "Windy" and last floor divide by attribute "Temperature". Tree is scan downwards and in each floor extended search filter add attribute.
<br>
<br>
<b>Example:</b> On start records are divide by column "Outlook". If rows with value sunny in column "outlook"
have all same value in column play write this filter as rule. In next step filter divide rows by two
attributes "outlook" and "humidity". For another passage tree to depth is filter extended with attributes.

<h3> Technologies </h3>
- Programming language: Java 7

