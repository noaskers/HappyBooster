# Booster plugin
Boosters via persmissie van luckperms

1 booster actief per keer.

Shop booster
Jobs booster
Skills booster
Alles booster (alle bovenstaande boosters)

Deze booster moet ik zelf kunnen maken en ook instelen hoeveel hun mutlipleyer is.

## Config:
```yaml
boosters:
  1:
    name: "Shop"
    lore:
      - "blablalba"
      - "blablalba"
    multiplier: 2 # 2x multiplier
    time: 1h # 1 uur
    activeer-bericht:
      - ""
      - "&aBOOSTER ACTIEF"
      - "balblal"
    commands: # Commands die worden uitgevoerd als de booster actief is.
      - "lp group default permission settemp shop.booster.{multiplier} {time}"
  
```


Via een gui alle boosters die jij hebt zien.
Bossbar die aangeeft hoe lang de booster nog actief is.

Gui waar je alle boosters kan zien die jij hebt.

## Commands:
/booster give <player> <booster>
/booster active <player> <booster>
/booster remove <player> <booster>
/booster list <player>
/boosters # Open de gui

GUi via triumphGUI

#   H a p p y B o o s t e r  
 