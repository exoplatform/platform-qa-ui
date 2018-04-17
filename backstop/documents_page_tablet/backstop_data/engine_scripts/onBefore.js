module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);
//ou avec casper 2
casper.then( function(){ 
    casper.echo('loggin');
    casper.waitForSelector('form', function(){
      this.fillSelectors('form', {
        'input[class="username"]':'root',
        'input[class="password"]':'gtn'
      }, true);
    casper.echo('Clicking button');
    casper.click('button.button');
    casper.wait(3000);
    });

  });

casper.then( function(){ 
    casper.echo('Clicking button menu');
    casper.click('div.mobile-visible.toggle-left-bar');
    casper.wait(1000);

  });

casper.then( function(){ 
    casper.echo('Clicking documents button');
    casper.click('i#MenuItemdocuments.uiIconPLFDocuments');
    casper.wait(4000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

}
