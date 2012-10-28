var __ai__;

var Event = function(type) {
    this.type = type
};

var EventTarget = function() {
    this.listeners = {};
};
EventTarget.prototype.dispatchEvent = function(event) {
    if (this["on" + event.type]) {
        this["on" + event.type].call(this, event);
    }

    var l = this.listeners[event.type];
    if (l) {
        for ( var i = 0, end = l.length; i < end; i++) {
            l[i].call(this, event);
        }
    }
};
EventTarget.prototype.addEventListener = function(eventType, listener) {
    if (this.listeners[eventType] == undefined) {
        this.listeners[eventType] = [];
    }

    this.listeners[eventType].push(listener);
}

var BattleAI = function(name) {
    if (name) {
        __ai__.name = name;
    }
};
BattleAI.prototype = new EventTarget();
BattleAI.prototype.getName = function() {
    return __ai__.name;
};
BattleAI.prototype.setName = function(name) {
    __ai__.name = name;
};
BattleAI.prototype.getAge = function() {
    return __ai__.age;
};
BattleAI.prototype.fire = function() {
    __ai__.fire();
};

var $ = new BattleAI();
