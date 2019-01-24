# DesignPatterns

Projekt věnovaný seznámení s základními návrhovými vzory používanými při vývoji softwaru. 
Jedná se o vzory patřící do skupiny GoF(tvořící, strukturální a vzory chování) implementované v programovacím jazyku Java.

## Co to je návrhový vzor ?
* obecné řešení problému, které se opakuje při vývoji softwaru
* není to knihovna nebo část zdrojového kodu, která by se dala přímo vložit do našeho systému, jedná se spíše o popis řešení problému
* každý vzor je popsán množinou komunikujících tříd
* hlavními tvůrci je skupina GoF (Gang of Four) - Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides - 1991

<p align='center'>
<img src="https://cms-assets.tutsplus.com/uploads/users/436/posts/22345/preview_image/simple-factory-design-pattern.jpg"/>
</p>

## Dělení
1. **Tvořící**: skupina návrhových vzorů řešící proces vytváření objektů
2. **Strukturální**: skupina návrhových vzorů řešící uspořádání jednotlivých tříd, objektů v systému
3. **Chování**: skupina návrhových vzorů zabývající se spoluprácí objektů mezi sebou

## 1. Návrhové vzory tvořící
### Továrna
* návrhový vzor pro zapouzdření složitejší inicializace instance místo v konstruktoru tovární metodou
* použití: tvorba složitějších formulářů, tvorba GUI pro více platforem

### Abstract Factory
* návrhový vzor snažící se řešit univerzálnost kodu a odstínit klienta od vytváření konktrétních instancí
* použití: tvorba grafického rozhraní pro více operačních systému

### Prototype
* návrhový vzor řešící jak vytvořit kopii existujícího objektu, místo vytváření nového
* použití: vhodné pro objekty kde je vytvoření nové instance časově hodně nákladné

### Singleton
* návrhový vzor zajišťující pouze jednu instanci dané třídy a poskytuje globální přístup k této instanci
* použítí: například grafické plátno ve ve vektorovém editoru je pouze jednou

### Builder
* návrhový vzor popisující jak přistupovat k tvorbě různých instancí s podobným konstrukčním procesem
* pro představu si můžeme představit stavbu domu nebo přípravu pizzy



## 2. Návrhové vzory strukturální
### Adapter
* návrhový vzor řešící komunikaci dvou tříd, které nemají kompatabilní rozhraní, převádí rozhraní jedné třídy na jiné

### Fasáda
* návrhový vzor poskytující jednotné rozhraní k sadě rozhraní v podsystému

### Dekorator
* návrhový vzor upřednostňující užití kompozice před dědičností, pokud chceme přidat vlastnost objektu tak vytvoříme nový objekt a ten původní zabalíme

### Composit
* návrhový vzor komponující objekty do stromové struktury a umožňuje klientovi pracovat s jednotlivými i se složenými objekty stejným způsobem



## 3. Návrhové vzory chování
### Observer
* návrhový vzor používaný v případě, kdy na jednom objektu je závislých mnoho dalších
* poskytuje způsob jak předat informaci o změně svého vnitřního stavu ostatním závislým objektům

### Command
* návrhový vzor zapouzdřuje požadavek jako objekt a umožňuje požadavky řadit do fronty nebo logovat

### Strategy
* Návrhový vzor slouží k vyměňování různých implementací algoritmu za běhu programu.
* například volba řadící algoritmu pole na základě jeho délky

### Iterátor
* návrhový vzor umožňující sekvenční procházení objektů uložených v nějaké složité struktuře (kontejneru)

### Chain of Responsibily
* návrhový vzor slouží k realizaci předávání požadavků v řetězci vzájemně propojených objektů. 
* objekt provede dílčí úkol a přepošle požadavek na další objekt v řetězci
* připomíná to předávání "horké brambory".

<p align='center'>
<img src="https://raw.githubusercontent.com/OmarElGabry/JavaDesignPatterns/master/diagrams/logo.png"/>
</p>


