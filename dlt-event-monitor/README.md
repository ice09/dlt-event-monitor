# Decoupled Message-Based Smart Contract Event Monitor

## Howto Use Message-based Event Handling

1. Start RabbitMQ Docker image with   
`docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management`

2. You can run this application and it publishes Ethereum Blockchain Events to a RabbitMQ Queue.

## Run Docker Version

* Create a file `config.yaml` with the correct contract address

```yaml
contracts:
   - address: <CONTRACT_ADDRESS>
     name: Switch
     events:
        - name: SwitchTurned(address,uint8)
          start: 0
        - name: UserChanged(address,address)
          start: 0
```

### 📖 Event Signatures

_Note: The Event name and signature must be correct, otherwise the hashing will not create the correct signature to match the Events. Read more about the topic hashing here: https://besu.hyperledger.org/en/stable/public-networks/concepts/events-and-logs/#event-signature-hash_

For our Events, the signatures are `SwitchTurned(address,uint8)` and `UserChanged(address,address)`, note the missing spaces between the parameter types and no parameter names (for `uint256` the type is just `uint`).

* Start Docker with local RabbitMQ and local Ethereum chain (eg. `anvil`)

```bash
docker run -p 8080:8080 \
-e CONFIG_FILE_PATH=/var/dlt-mon/config.yaml \
-e WEB3_ETHEREUM_RPC_URL=http://host.docker.internal:8545 \
-e SPRING_RABBITMQ_HOST=host.docker.internal \
-v $(pwd):/var/dlt-mon \
ice0nine/dlt-event-monitor:main
```
## Build Locally

### Prerequisites

* Foundry
* Java 17

### Setup

* Start RabbitMQ
* Start `anvil` (local development chain)

### In Bex REST Services Folder

* Run `./run-local.sh`

### Query Active Config

* Open http://localhost:8080/api/config

### Used Accounts

_Note: These are default accounts for mnemonic "test test ... junk"_

```
Available Accounts
==================

(0) 0xf39fd6e51aad88f6f4ce6ab8827279cfffb92266 (10000 ETH)
(1) 0x70997970c51812dc3a010c7d01b50e0d17dc79c8 (10000 ETH)
(2) 0x3c44cdddb6a900fa2b585dd299e03d12fa4293bc (10000 ETH)
(3) 0x90f79bf6eb2c4f870365e785982e1f101e93b906 (10000 ETH)
(4) 0x15d34aaf54267db7d7c367839aaf71a00a2c6a65 (10000 ETH)
(5) 0x9965507d1a55bcc2695c58ba16fb37d819b0a4dc (10000 ETH)
(6) 0x976ea74026e726554db657fa54763abd0c3a0aa9 (10000 ETH)
(7) 0x14dc79964da2c08b23698b3d3cc7ca32193d9955 (10000 ETH)
(8) 0x23618e81e3f5cdf7f54c3d65f7fbc0abf5b21e8f (10000 ETH)
(9) 0xa0ee7a142d267c1f36714e4a8f75612f20a79720 (10000 ETH)

Private Keys
==================

(0) 0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80
(1) 0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d
(2) 0x5de4111afa1a4b94908f83103eb1f1706367c2e68ca870fc3fb9a804cdab365a
(3) 0x7c852118294e51e653712a81e05800f419141751be58f605c371e15141b007a6
(4) 0x47e179ec197488593b187f80a00eb0da91f1b9d0b13f8733639f19c30a34926a
(5) 0x8b3a350cf5c34c9194ca85829a2df0ec3153be0318b5e2d3348e872092edffba
(6) 0x92db14e403b83dfe3df233f83dfa3a0d7096f21ca9b0d6d6b8d88b2b4ec1564e
(7) 0x4bbbf85ce3377467afe5d46f804f221813b2bb87f24d81f60f1fcdbf7cbf4356
(8) 0xdbda1821b80551c9d65939329250298aa3472ba22feea921c0cf5d620ea67b97
(9) 0x2a871d0798f97d79848a013d4936a73bf4cc922c825d33c1cf7073dff6d409c6

DEPLOYER = 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266;
BUYER    = 0x70997970C51812dc3A010C7d01b50e0d17dc79C8;
SUPPLIER = 0x3C44CdDdB6a900fa2b585dd299e03d12FA4293BC;
INVESTOR = 0x90F79bf6EB2c4f870365E785982E1f101E93b906;
```

## Run on Goerli Arbitrum

1. Change the Alchemy API key in `run-goerli-arbitrum.sh`
2. Run `./run-goerli-arbitrum.sh`

### Used Accounts on Goerli

_Mnemonic: **cigar phone chief vast giant off thumb average add degree weekend genius**_

```
Available Accounts
==================

(0) 0x97e37b991610c8ef6d2b00532bc7e6e8dd87447d (10000 ETH)
(1) 0x634571af35d43c2d1cc3de00706179910d869b94 (10000 ETH)
(2) 0xbb5ae9bc103cca477dfd1b656b131c414a76afdf (10000 ETH)
(3) 0xa5cf663bc60bd325cae6d9a7fbbba8c190562894 (10000 ETH)
(4) 0xe6c60e8b31ee7c8052322ba26ebcf64c132ad4ea (10000 ETH)
(5) 0xf7ca12d512c57679a3b388e09f41a92607c7b5a6 (10000 ETH)
(6) 0xe9c634bbbf867851b213065519b3f173dec3fb2f (10000 ETH)
(7) 0x3407982d3bd7982e626a9ad7298696116b82df89 (10000 ETH)
(8) 0xd4557a7e1cda060d9128baff42f5c07ded16f3ce (10000 ETH)
(9) 0x9bfa379ebf0b766d99c3dd25092e4e7d2039c48c (10000 ETH)

Private Keys
==================

(0) 0x2e82254a0c1555f5e3d953e264b237bf77de08880278dcc63dc9f0263b42eed7
(1) 0xda8f0eb52ceff6033f35fd9da2ec06fae531b712c1a0ffcc6502fcfc0f1d8dfb
(2) 0xc7c65cb1c35e4a2575325cbadb91d828cb51b6a55f90cd930e55a1b550a514d8
(3) 0xb87aee2589045e5222442f87f8cf4a1bceaec2a70193d9a3adfb67258adb23a3
(4) 0x72ee558bd356eaa2f74382368bc4ac8836b24f967a31ee81881c13adb9b2b735
(5) 0xc19c9166be11158933942d47f0eb9233827812188b2df8b50ee0e968ecabc8f3
(6) 0xb820b60e8a27c561d590c53bcff0f70f8054d00c40c34d9e6b6631bc3b348f29
(7) 0xc752462f12070a0c5b6cea2268a4249e757ef8b0479c1324af6ffaa597a392c8
(8) 0xaf7feb4d39da31da46a7b26823fa1c1f526c09c3f5b9e752dfdcf207ce176783
(9) 0x774a6f5d1c711c37b17161e5de529f43646ecda2e31b8429e457412e483f7a02

DEPLOYER = 0x97e37b991610c8ef6d2b00532bc7e6e8dd87447d (10000 ETH)
BUYER    = 0x634571af35d43c2d1cc3de00706179910d869b94 (10000 ETH)
INVESTOR = 0xbb5ae9bc103cca477dfd1b656b131c414a76afdf (10000 ETH)
SUPPLIER = 0xa5cf663bc60bd325cae6d9a7fbbba8c190562894 (10000 ETH)
```
