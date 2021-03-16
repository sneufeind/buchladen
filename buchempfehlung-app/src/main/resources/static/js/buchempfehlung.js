function ladeBuecher() {
    console.debug("Lade Bücher...");
    $.get('/api/buecher', function(data, status){
        var buecher = data && data.buecher ? data.buecher : [];
        console.debug(data.buecher.length +" Bücher geladen!");

        // add items
        var buchItem = ({rang, titel, autor, genre}) => `
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-2">
                            <h4>Platz ${rang}</h4>
                        </div>
                        <div class="col-md-8">
                            <div class="row">
                                <div class="col-md-8">
                                    <h4>${titel}</h4>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8">
                                    <p>${autor}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <h4>${genre}</h4>
                        </div>
                    </div>
                </li>
        `;
        $('#buecher').html( buecher.map(buchItem).join('') );
    });
}
