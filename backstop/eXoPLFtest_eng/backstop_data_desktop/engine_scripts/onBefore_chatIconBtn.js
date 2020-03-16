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
      casper.echo('Clicking on Chat Icon');
      casper.waitForSelector('.dropdown.status-dropdown');
      casper.mouse.move('.dropdown.status-dropdown')
      casper.wait(4000);
      casper.click('.dropdown.status-dropdown');
      casper.wait(4000);
      casper.click('.uiNotifChatIcon');

    });


casper.thenOpen(scenario.url + "portal/intranet/chat", function() {
    casper.echo('Clicking portalePage button');
    casper.wait(4000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');
  });
}
