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
    casper.wait(8000);
    });
   }
  });

casper.thenOpen(scenario.url + "/portal/intranet/wiki", function() {
    casper.echo('Wiki Page is displayed');
    casper.wait(8000);
    casper.echo('Click on add page');
    casper.click('.uiWikiPageControlArea li:nth-of-type(2) div');
    casper.echo('Add wiki blank page');
    casper.click('.uiWikiPageControlArea li:nth-of-type(2) li:nth-of-type(1)');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');
  });



}
