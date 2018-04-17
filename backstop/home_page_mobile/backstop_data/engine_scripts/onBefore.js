// onReady example
module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);  
  casper.then( function(){
    casper.echo('capture login page');
    casper.wait(1000);
    this.capture('../../backstop_data/user-action-screenshots/' + vp.name+'.png');
  });
  
}