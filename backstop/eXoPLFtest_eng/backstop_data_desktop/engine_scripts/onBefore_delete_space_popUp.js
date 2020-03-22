// onReady example
module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);
//ou avec casper 2
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
    casper.wait(8000);
    });
   }
  });

casper.then( function(){
    casper.echo('create spacefor calendar');
    casper.click('div.joinSpace a');
    casper.wait(3000);

  });

casper.then( function(){
    casper.echo('Clicking button delete');
    casper.waitForSelector('button.confirmPopup');
    casper.click('button.confirmPopup');
    casper.echo('button clicked');
    casper.wait(3000);
  });

  }
