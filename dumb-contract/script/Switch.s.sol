// SPDX-License-Identifier: MIT-0
pragma solidity ^0.8.13;

import "forge-std/Script.sol";
import "../src/Switch.sol";

contract SwitchScript is Script {
    function setUp() public {}

    function run() external {
        vm.broadcast(0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80);
        Switch _switch = new Switch();
    }
}
