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

casper.then( function(){
    casper.echo('Clicking portal button');
    casper.click('div#UISetupPlatformToolBarPortlet a');
    casper.waitForSelector('i.uiIconPLFProfile');
    casper.wait(8000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

casper.wait(8000, function() {
    casper.echo('Clicking spaces button');
    casper.mouse.move('.dropdown-submenu:nth-of-type(5)');
    casper.waitForSelector('.dropdown-menu :nth-of-type(5) li:nth-of-type(2) a');
    casper.wait(8000);
    casper.click('.dropdown-menu :nth-of-type(5) li:nth-of-type(2) a');
  });

}
