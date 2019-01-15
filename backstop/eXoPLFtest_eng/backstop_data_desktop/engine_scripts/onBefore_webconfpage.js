module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);

casper.then( function(){
    casper.echo('loggin');
    if (this.exists('div.loginButton')) {
    casper.waitForSelector('form', function(){
      this.fillSelectors('form', {
        'input[id="username"]':'root',
        'input[id="password"]':'gtn'
      }, true);
    casper.echo('Clicking button');
    casper.click('button.button');
    casper.wait(4000);
    });
   }
  });

}
