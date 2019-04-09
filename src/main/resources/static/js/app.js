var heroData = {
    name: 'undefined',
    level: 0,
    hpBasic: 0,
    atkBasic: 0,
    mgkBasic: 0,
    atkDefBasic: 0,
    mgkDefBasic: 0,
    hpNow: 0,
    atk: 0,
    mgk: 0,
    atkDef: 0,
    mgkDef: 0
};

var hero = new Vue({
    el: '#hero',
    data: heroData
});

var enemyNowData = {
    name: 'undefined',
    level: 0,
    hpBasic: 0,
    atkBasic: 0,
    mgkBasic: 0,
    atkDefBasic: 0,
    mgkDefBasic: 0,
    hpNow: 0,
    atk: 0,
    mgk: 0,
    atkDef: 0,
    mgkDef: 0
};

var enemyNow = new Vue({
    el: '#enemyNow',
    data: enemyNowData
});

var itemData = {
    name: 'None',
    level: 0,
    enhanceLevel: 0,
    bagItemType: 'None'
};

var itemInfo = new Vue({
    el:'#itemInfo',
    data: itemData,
    methods: {
        equip: function (index) {
            $.ajax({
                url: '/equip?index=' + index,
                method: 'GET',
                async: false,
                success: function (data) {
                    refreshHero();
                    refreshBagInfo();
                    getSlotInfo();
                    getSlotItemInfo(data);
                },
                error:function (e) {
                    alert(e);
                }
            });
        },
        unLoad: function (index) {
            $.ajax({
                url: '/unLoad?index=' + index,
                method: 'GET',
                async: false,
                success: function (data) {
                    refreshHero();
                    refreshBagInfo();
                    getSlotInfo();
                    getItemInfo(0);
                },
                error:function (e) {
                    alert(e);
                }
            });
        },
        sell: function (index) {
            $.ajax({
                url: '/sell?index=' + index,
                method: 'GET',
                async: false,
                success: function (data) {
                    getItemInfo(0);
                    refreshGold();
                    refreshBagInfo();
                },
                error:function (e) {
                    alert(e);
                }
            });
        },
        enhance: function (index) {
            $.ajax({
                url: '/enhance?index=' + index,
                method: 'GET',
                async: false,
                success: function (data) {
                    getItemInfo(index);
                    refreshGold();
                    if(!data)
                        alert('强化失败');
                },
                error:function (e) {
                    alert(e);
                }
            });
        }
    }
});

var goldData = {gold: 0};

var gameControl = new Vue({
    el: '#gameControl',
    data: goldData,
    methods: {
        nextEnemy: function () {
            $.ajax({
                url: '/next',
                method: 'GET',
                async: false,
                success: function (data) {
                    refreshEnemy();
                },
                error:function (e) {
                    alert(e);
                }
            });
        },
        fight: function () {
            $.ajax({
                url: '/fight',
                method: 'GET',
                async: false,
                success: function (data) {
                    refreshEnemy();
                    refreshHero();
                    refreshBagInfo();
                    getLogInfo();
                    if(!data)
                        alert("You lose.")
                },
                error:function (e) {
                    alert(e);
                }
            });
        }
    }
});

function refreshGold() {
    $.ajax({
        url: '/getGold',
        method: 'GET',
        async: false,
        success: function (data) {
            concat(goldData, data);
        },
        error:function (e) {
            alert(e);
        }
    });
}

function refreshHero() {
    $.ajax({
        url: '/refreshHero',
        method: 'GET',
        async: false,
        success: function (data) {
            concat(heroData, data);
        },
        error:function (e) {
            alert(e);
        }
    });
}

function refreshEnemy() {
    $.ajax({
        url: '/refreshEnemy',
        method: 'GET',
        async: false,
        success: function (data) {
            concat(enemyNowData, data);
        },
        error:function (e) {
            alert(e);
        }
    });
}


function getItemInfo(index) {
    $.ajax({
        url: '/getItemInfo?index=' + index,
        method: 'GET',
        async: false,
        success: function (data) {
            concat(itemData, data);
            itemData.isEquipped = false;
            itemData.index = index;
            itemInfo.$forceUpdate();
        },
        error:function (e) {
            alert(e);
        }
    });
}

function getSlotItemInfo(slot) {
    $.ajax({
        url: '/getSlotItemInfo?slot=' + slot,
        method: 'GET',
        async: false,
        success: function (data) {
            concat(itemData, data);
            itemData.isEquipped = true;
            itemData.index = slot;
            itemInfo.$forceUpdate();
        },
        error:function (e) {
            alert(e);
        }
    });
}
var slotData = {
    skill0: {name: "none", level: 0},
    skill1: {name: "none", level: 0},
    skill2: {name: "none", level: 0},
    skill3: {name: "none", level: 0},
    weapon: {name: "none", level: 0},
    helmet: {name: "none", level: 0},
    breastplate: {name: "none", level: 0},
    boot: {name: "none", level: 0}
};

var slotInfo = new Vue({
    el: "#slotInfo",
    data: slotData,
    methods: {
        getSlotItemInfo: getSlotItemInfo,
        byLevel: function (level) {
            return {
                'btn-outline-default': level === 0,
                'btn-success': level === 1,
                'btn-info': level === 2,
                'btn-warning': level === 3
            }
        }
    }
});

function getSlotInfo() {
    $.ajax({
        url: '/getSlotInfo',
        method: 'GET',
        async: false,
        success: function (data) {
            concat(slotData, data);
        },
        error:function (e) {
            alert(e);
        }
    });
}

var bagData = {
    slots:[]
};
var bagInfo = new Vue({
    el: '#bagInfo',
    data: bagData,
    methods: {
        getInfo: getItemInfo,
        byLevel: function (level) {
            return {
                'btn-outline-default': level === 0,
                'btn-success': level === 1,
                'btn-info': level === 2,
                'btn-warning': level === 3
            }
        }
    },
    computed: {

    }
});

function refreshBagInfo() {
    $.ajax({
        url: '/refreshBagInfo',
        method: 'GET',
        async: false,
        success: function (data) {
            bagData.slots = data;
        },
        error:function (e) {
            alert(e);
        }
    });
}

var logData = {
    log: ['No logs']
};
var logInfo = new Vue({
    el: "#logInfo",
    data: logData
});

function getLogInfo() {
    $.ajax({
        url: '/getBattleLog',
        method: 'GET',
        async: false,
        success: function (data) {
            logData.log = data
        },
        error:function (e) {
            alert(e);
        }
    });
}

function concat(jsonbject1, jsonbject2){
    for (var attr in jsonbject2) {
        if(jsonbject2[attr] === null)
            continue;
        if(!isNaN(jsonbject2[attr])){
            jsonbject1[attr] = jsonbject2[attr].toFixed(1);
        }else {
            jsonbject1[attr] = jsonbject2[attr];
        }
    }
}

refreshHero();
refreshEnemy();
refreshBagInfo();
getSlotInfo();
// getItemInfo(0);