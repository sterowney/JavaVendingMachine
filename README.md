=== INSTRUCTIONS ===

Basic maven product with Junit as dependency from Maven central. I hope you like it! Steven


=== MY NOTES ===

Little readme with my to do list explaining what it does  (almost a mini backlog in logically prioritised order)

Vending Machine Features:

feature turn on/off vending machine

feature list products

feature insert coins
    - valid coin
    - invalid coin

feature get total money inserted

feature select product
    - no money/not enough inserted
    - exact amount inserted
    - give change
    - product code doesn't exist
    - no stock left


SPECIFICATION

The valid set of actions on the vending machine is:
* INSERT MONEY – Ten Pence, Twenty Pence, Fifty Pence, Pound
* COIN RETURN – returns all inserted money
* GET-A, GET-B, GET-C – selects item A (£0.60), B (£1), or C (£1.70)
* TURN-ON, TURN-OFF – turns the machine on and off

The valid set of responses from the vending machine is:
* Ten Pence, Twenty Pence, Fifty Pence, Pound – return coin
* A, B, C – vend item A, B, or C
* On / Off – turn on / off

The vending machine must track the following state:
* Available items – each item has a count, a price, and a selector (A, B or C)
* Available change – # of Ten Pence, Twenty Pence, Fifty Pence, and Pounds available
* Currently inserted money
* Whether the machine is on or off