## NoHaser
This anticheat will follow the [Minecraft Packets Protocol 776 (26.2)](https://minecraft.wiki/w/Java_Edition_protocol/Packets?oldid=3786274). It is largely inspired by (and built from) its sister anticheat made for Minecraft 1.8.8 - [NoHaserBenis](https://github.com/Poooooooooolch/NoHaserBenis).

Although we will convert packet classes into our own classes, we will be using [PacketEvents 2.14.0](https://github.com/retrooper/packetevents/releases/tag/v2.14.0) to listen to packets with Paper as our server platform on 26.2.

## Installation
To build the project, you will need to have [Maven](https://maven.apache.org/) installed. You can then run the following command in the project directory:

```bash
mvn clean package
```

Then move the generated jar file from the `target` directory to your Paper 26.2 server's `plugins` folder. Move the [correct PacketEvents jar](https://github.com/retrooper/packetevents/releases/download/v2.14.0/packetevents-spigot-2.14.0.jar) file to the `plugins` folder as well.