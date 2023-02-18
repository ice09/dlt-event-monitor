# Switch Dumb Smart Contract

We are using a _Dumb Smart Contract_ here. A _Dumb Smart Contract_ is a Smart Contract pattern that takes advantage of Events, and their relative cheapness compared to writing to storage. 

## Smart Contract Structure

The stucture of the `Switch` contract is very simple, it provides two functions:

1. `turnSwitch(uint8 state)` which emits the Event `SwitchTurned(address,uint8)` if the user is allowed to call the function.
2. `changeUser(address newUser)` which changes the address of the user and emits the Event `UserChanged(address)` if the user is allowed to change the user.

![](https://i.imgur.com/JD93P2g.png)

```solidity
event SwitchTurned(address indexed sender, uint8 indexed state);
event UserChanged(address indexed oldUser, address indexed newUser);

function changeUser(address newUser) public {
    //...
    emit UserChanged(user, newUser);
}

function turnSwitch(uint8 state) public {
    //...
    emit SwitchTurned(msg.sender, state);
}
```

## Dumb Smart Contract Pattern

The idea is to listen to the specified Events (`SwitchTurned` and `UserChanged`) at the address of the deployed contract. 

As the Events are only emitted if the transaction succeeds, a listener to these events can derive the current switch state from the sequence of events. 

Using this pattern, no storage is used and usage is much cheaper.

Read more about the Dumb Smart Contracts pattern here: https://anallergytoanalogy.medium.com/adventures-with-dumb-contracts-18f8ce8414c9

## Build & Test

* `forge -vvvv test`

## Start local Anvil

* `anvil` (uses default mnemonic **test test test ... junk**)

## Deploy to local Anvil

* `forge script script/Switch.s.sol -vvvv --broadcast --fork-url=http://localhost:8545`
