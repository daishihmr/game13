$.onload = function(e) {
    println("on" + e.type);
    this.setName("Daishi 1");
    println("hello. my name is " + this.getName());
    this.fire();
};
