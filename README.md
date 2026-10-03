# Wacko Beacons（Fabric 26.2 移植版）

原项目：https://github.com/getcmdrolled/wacko-beacons （原作者 GetCmdRolled，CC0 协议）

这是一个概念验证（PoC）客户端模组：利用 Minecraft 服务端对信标效果校验的漏洞，
让玩家在客户端即可给低层信标选择高级效果（如一层信标拿生命恢复 II、力量 I 等）。

## 移植说明（1.21.4 → 26.2）

- **映射体系**：Minecraft 26.1 起官方不再混淆代码，Yarn 映射在 1.21.11 后停更。
  本移植直接使用 Mojang 官方命名，移除了 `mappings` 依赖。
- **工具链**：Loom 1.18.2 + Fabric Loader 0.19.5 + Gradle 9.7.1 + Java 25。
- **依赖变化**：非混淆模式下 Loom 不再注册 `modImplementation`，改用 `implementation`；
  本模组不使用 Fabric API，已移除该依赖。
- **类名/成员名迁移**：
  - `net.minecraft.block.entity.BeaconBlockEntity` → `net.minecraft.world.level.block.entity.BeaconBlockEntity`
  - 字段 `EFFECTS_BY_LEVEL` → `BEACON_EFFECTS`
  - `StatusEffect(s)` → `MobEffect(s)`，`RegistryEntry` → `Holder`
  - `BeaconScreen$EffectButtonWidget` → `BeaconScreen$BeaconPowerButton`
  - 按钮方法 `tick(int)` → `updateStatus(int)`
  - `ClickableWidget` → `AbstractWidget`，`Text` → `Component`
- Mixin `compatibilityLevel` 提升至 `JAVA_25`（Loader 0.19.x 内置 Mixin 0.8.7 支持）。
- `fabric.mod.json`：`environment` 改为 `client`，`depends` 改为 `minecraft >=26.2 <26.3`、`java >=25`。
- 图标因原仓库二进制资源无法完整获取，使用程序生成的替代图标。

## 构建

需要 JDK 25：

```bash
./gradlew build
```

产物在 `build/libs/` 下。

## 功能

- `BeaconBlockEntityMixin`：把「生命恢复」加入客户端信标第二层级效果列表。
- `BeaconScreenButtonMixin`：让信标界面所有效果按钮始终可点（无视信标塔层级）。

> 仅在客户端生效；实际能否在服务端生效取决于服务端版本是否仍有该校验漏洞。
