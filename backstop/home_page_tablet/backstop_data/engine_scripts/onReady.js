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
    casper.echo( 'Clicking button' );
    casper.click('button.button');
    });

  });

casper.then( function(){  
    casper.echo('waiting for page to load');
    casper.wait(8000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });
  
}
