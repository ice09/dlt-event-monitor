// SPDX-License-Identifier: MIT-0
pragma solidity ^0.8.13;

import "forge-std/Test.sol";
import "../src/Switch.sol";

contract SwitchTest is Test {
    Switch public _switch;

    event SwitchTurned(address indexed sender, uint8 indexed state);
    event UserChanged(address indexed oldUser, address indexed newUser);

    function setUp() public {
        vm.prank(address(0));
        _switch = new Switch();
    }

    function testSwitch() public {
        vm.expectEmit(true, true, false, true);
        emit SwitchTurned(address(0), 1);
        vm.prank(address(0));
        _switch.turnSwitch(1);
    }

    function testFailChangeUser() public {
        _switch.changeUser(0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266);
    }

    function testChangeUser() public {
        vm.expectEmit(true, true, false, true);
        emit UserChanged(address(0), 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266);
        vm.prank(address(0));
        _switch.changeUser(0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266);
    }
}