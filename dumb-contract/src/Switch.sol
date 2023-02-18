// SPDX-License-Identifier: MIT-0
pragma solidity >=0.7.0 <0.9.0;

contract Switch {

    address private user;

    event SwitchTurned(address indexed sender, uint8 indexed state);
    event UserChanged(address indexed oldUser, address indexed newUser);

    constructor() {
        user = msg.sender;
    }

    function changeUser(address newUser) public {
        require(msg.sender == user, "message sender is not allowed change user");
        emit UserChanged(user, newUser);
        user = newUser;
    }

    function turnSwitch(uint8 state) public {
        require(msg.sender == user, "message sender is not allowed to turn switch");
        emit SwitchTurned(msg.sender, state);
    }

}