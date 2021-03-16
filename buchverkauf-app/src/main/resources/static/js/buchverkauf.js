function ladeBuecher() {
    console.debug("Lade Bücher...");
    $.get('/api/buecher', function(data, status){
        var buecher = data && data.buecher ? data.buecher : [];
        console.debug(data.buecher.length +" Bücher geladen!");

        // map viewData
        var viewData = [];
        var index = 1;
        buecher.forEach(function(item){
            viewData.push({
                id:     'kaufenBtn'+index++,
                titel:  item.titel,
                isbn:   item.isbn,
                preis:  item.preisInEuro.toLocaleString('de-DE', { maximumFractionDigits: 2, minimumFractionDigits: 2 }),
                kaufenUrl: `/api/buecher/${item.isbn}/kaufen`,
            });
        });

        // add items
        var buchItem = ({id, titel, isbn, preis}) => `
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="row">
                                <div class="col-md-8">
                                    <h4>${titel}</h4>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8">
                                    <p>${isbn}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <h4>${preis} &euro;</h4>
                        </div>
                        <div class="col-md-2">
                            <button type="button" id="${id}" class="btn btn-primary btn-lg">
                                <span class="glyphicon glyphicon-shopping-cart glyphicon glyphicon-" aria-hidden="true"></span> Kaufen
                            </button>
                        </div>
                    </div>
                </li>
        `;
        $('#buecher').html( viewData.map(buchItem).join('') );

        // add 'kaufen-on-click'
        viewData.forEach(data => {
            $(`#${data.id}`).click(function(){
                $.ajax({
                    url: data.kaufenUrl,
                    type: 'POST',
                    headers: {
                        'Accept' : 'application/json',
                        'Content-Type': 'application/json',
                    },
                    success: function (res){
                        console.info(`Buch ${data.titel} mit ISBN ${data.isbn} gekauft!`);
                        alert('Gekauft!');
                    },
                    error: function (data){
                        console.error(data);
                        alert('Sorry, da ist etwas schief gegangen...\nVersuchen Sie es später erneut!');
                    },
                });
            });
        });
    });
}
