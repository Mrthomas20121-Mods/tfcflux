const path = require('path');
const fs = require('fs');

const assetspath = path.join('./src', 'main', 'resources', 'assets', 'tfcflux');
const modelPath = path.join(assetspath, 'models');

const metals = [
  'copper',
  'tin',
  'lead',
  'silver',
  'bronze',
  'bismuth',
  'black_bronze',
  'bismuth_bronze',
  'wrought_iron',
  'red_steel',
  'blue_steel',
  'steel',
  'black_steel',
  'aluminium',
  'cobalt',
  'antimony',
  'titanium',
  'ardite',
  'nickel_silver',
  'red_alloy',
  'invar',
  'mithril',
  'electrum',
  'tungsten',
  'tungsten_steel',
  'osmium',
  'constantan',
  'manyullyn',
  'gold',
  'brass',
  'zinc',
  'nickel',
  'rose_gold',
  'sterling_silver',
  'pig_iron',
  'platinum',
  'weak_steel',
  'weak_black_steel',
  'weak_blue_steel',
  'weak_red_steel',
  'high_carbon_steel',
  'high_carbon__black_steel',
  'high_carbon_blue_steel',
  'high_carbon_red_steel',
  'aluminium_brass'
]

const ores = [
    'native_copper',
    'native_gold',
    'native_platinum',
    'hematite',
    'native_silver',
    'cassiterite',
    'galena',
    'bismuthinite',
    'garnierite',
    'malachite',
    'magnetite',
    'limonite',
    'sphalerite',
    'tetrahedrite',
    'bituminous_coal',
    'lignite',
    'kaolinite',
    'gypsum',
    'satinspar',
    'selenite',
    'graphite',
    'kimberlite',
    'petrified_wood',
    'sulfur',
    'jet',
    'microcline',
    'pitchblende',
    'cinnabar',
    'cryolite',
    'saltpeter',
    'serpentine',
    'sylvite',
    'borax',
    'olivine',
    'lapis_lazuli',
    'native_ardite',
    'rutile',
    'native_osmium',
    'bauxite',
    'wolframite',
    'cobaltite',
    'thorianite',
    'chromite',
    'pyrolusite',
    'magnesite',
    'boron',
    'spodumene',
    'stibnite',
	'mawsonite'
]

let types = [
	'normal',
	'rich',
	'poor',
	'small'
]

function stringify(json) {
  return JSON.stringify(json, null, 2)
}

function genModels() {
  const metalPath = path.join(modelPath, 'item', 'metal');
  const orePath = path.join(modelPath, 'item', 'ore');
  const dustPath = path.join(metalPath, 'powder');
	
  genMetalModels(metalPath, dustPath);
  genOreModels(orePath);
}

function genOreModels(orePath) {
  for(ore of ores) {
	for(type of types) {
	  let model = {
        "parent": "item/generated",
        "textures": {
          "layer0": `tfcflux:items/ore/pulverized/${type}/${ore}`
        }
      }
	  fs.writeFileSync(path.join(orePath, 'pulverized', type, `${ore}.json`), stringify(model));
	  model = {
        "parent": "item/generated",
        "textures": {
          "layer0": `tfcflux:items/ore/purified/${type}/${ore}`
        }
      }
	  fs.writeFileSync(path.join(orePath, 'purified', type, `${ore}.json`), stringify(model));
	}
  }
}

function genMetalModels(metalPath, dustPath) {
  for(metal of metals) {
    let model = {
      "parent": "item/generated",
      "textures": {
        "layer0": `tfcflux:items/metal/powder/${metal}`
      }
    }
    
    fs.writeFileSync(path.join(dustPath, `${metal}.json`), stringify(model));
  }
}
// models
genModels();

function genLang() {
	let lang = "";
	
	for(metal of metals) {
		lang = lang.concat(`item.tfcflux.metal.powder.${metal}.name=${capitalize(metal)} Powder\n`);
	}
	for(ore of ores) {
		lang = lang.concat(`item.tfcflux.ore.pulverized.normal.${ore}.name=Pulverized ${capitalize(ore)}\n`);
		lang = lang.concat(`item.tfcflux.ore.pulverized.rich.${ore}.name=Rich Pulverized ${capitalize(ore)}\n`);
		lang = lang.concat(`item.tfcflux.ore.pulverized.poor.${ore}.name=Poor Pulverized ${capitalize(ore)}\n`);
    lang = lang.concat(`item.tfcflux.ore.pulverized.small.${ore}.name=Small Pulverized ${capitalize(ore)}\n`);
    
    lang = lang.concat(`item.tfcflux.ore.purified.normal.${ore}.name=Purified ${capitalize(ore)}\n`);
		lang = lang.concat(`item.tfcflux.ore.purified.rich.${ore}.name=Rich Purified ${capitalize(ore)}\n`);
		lang = lang.concat(`item.tfcflux.ore.purified.poor.${ore}.name=Poor Purified ${capitalize(ore)}\n`);
		lang = lang.concat(`item.tfcflux.ore.purified.small.${ore}.name=Small Purified ${capitalize(ore)}\n`);
	}
	fs.writeFileSync(path.join(assetspath, 'lang', 'en_us.lang'), lang)
}

/**
 * 
 * @param {String} str 
 */
function capitalize(str) {
  if(str.includes('_')) {
    let data = str.split('_');
    for (let index = 0; index < data.length; index++) {
      data[index] = cap(data[index])
    }
    return data.join(' ')
  }
  return cap(str);
}
function cap(str='') {
  return str.charAt(0).toUpperCase()+str.slice(1, str.length);
}

// language file
//genLang();