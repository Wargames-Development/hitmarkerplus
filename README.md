<!-- Wargames Development Group – Foliage+ -->

<!-- Badges (enable when/if you publish on these platforms) -->
<!-- [![CurseForge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/curseforge_vector.svg)]() -->
<!-- [![Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)]() -->

[![Discord](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/social/discord-plural_vector.svg)](https://discord.wargames.uk)

# hitmarkerplus (Forge 1.7.10)

**hitmarkerplus** is a lightweight Forge **1.7.10** mod that gives flans guns a satisfying hitmarker ding sound when they damage a player:
- Players and all entities damaged by flans weapons will trigger this sound

Repository: https://github.com/Wargames-Development/hitmarkerplus

---

## Features

### Hitmarker Sound
- plays a xp orb sound when entity is damaged by a flans bullet

---

## Installation

### Client
1. Install Forge **1.7.10 (10.13.4.1614)**
2. Drop the hitmarkerplus jar into your `mods/` folder
3. Launch the game

### Dedicated Server
1. Install Forge **1.7.10 (10.13.4.1614)**
2. Drop the hitmarkerplus jar into your server `mods/` folder
3. Start the server

---

## Building from Source

If you wish to compile the mod yourself:

### Requirements
- Java 8 JDK
- Git
- Windows, Linux, or macOS

### Steps

1. Clone the repository:

       git clone https://github.com/Wargames-Development/hitmarkerplus.git

2. Navigate into the project directory.

3. Build the mod:

       gradlew build

4. The compiled JAR will be located in:

       build/libs/

> Note: Development builds may be unstable and are not guaranteed to match release behavior.

---

## Contributing

Contributions are welcome from anyone with Forge modding experience.

Please note:
- This project targets **Minecraft 1.7.10** specifically.
- Familiarity with ForgeGradle, ASM, and legacy Forge APIs is strongly recommended.

### General Guidelines
- Keep changes focused and well-documented.
- Avoid unnecessary refactors.
- Test changes in both singleplayer and multiplayer environments where applicable.

If you would like to contribute in a more official capacity, please contact us through our Discord server.

---

## Need to get in touch?

Our primary community hub is our Discord server:

https://discord.wargames.uk

For non-support enquiries:
- **dev@wargames.uk** — development / project enquiries
- **abuse@wargames.uk** — security or abuse reports

> Please note: email is **not** used for mod support.  
> Use Discord for questions, feedback, or bug reports.

---

## Credits

### Development
This mod is developed and maintained by the **Wargames Development Group (WDG)**.

Primary development:
- **Robbie** — Lead developer  
  https://github.com/RobbieMilkovic

### Contributors

[![Contributors](https://contrib.rocks/image?repo=Wargames-Development/hitmarkerplus)](https://github.com/Wargames-Development/hitmarkerplus/graphs/contributors)

---

### Acknowledgements

While all code in this repository is original, implementation approaches were informed by:
- Common Forge 1.7.10 modding practices
- Observed behavior in large legacy mods (e.g. HBM-style sound handling)

No third-party code has been directly copied.

---

### Wargames Development Group Team

- [Glac](https://github.com/RhysHopkins04) - Developer
- [Barrack](https://github.com/BateNacon) - Developer
- [Robbie](https://github.com/RobbieMilkovic) - Developer
- [Jomp](https://github.com/jomples) - Developer
- [Ocean](https://github.com/Oceanseaj) - Advisor
- [Viking](https://github.com/snowboardman91) - Advisor

---

## License & Usage

This project is provided as-is for use in modpacks and private servers.

Please respect the authorship of this repository when redistributing or modifying the code.

---

## Links

- GitHub Repository: https://github.com/Wargames-Development/hitmarkerplus
- Discord: https://discord.wargames.uk

---