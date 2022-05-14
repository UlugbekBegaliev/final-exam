<#import 'partials/main.ftlh' as main>
<@main.renderWith scripts=["/js/script.js"]>
    <h3 class="font-weight-bold place-header">${place.name}</h3>
    <div class="place-body" id="place-body">
        <img class="place-image" src="${place.image}" alt="${place.name}">
        <div>
            <p>${place.description}</p>
        </div>
        <p>Рейтинг: ${place.rating}</p>

        <#if placeImages??>
            <div class="wrapper-photo">
                <#list placeImages as place>
                    <div class="place-card-photo">
                        <div class="flex-column box-400">
                            <img class="place-image-photo" src="${place.image}" alt="image.jpg">
                        </div>
                        </a>
                    </div>
                </#list>
            </div>
        </#if>
        <div class="modal-dialog" id="modal_window" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Отзыв:</h5>
                </div>
                <div class="modal-body">
                    <form id="review-form" class="reviewForm" name="review-form" enctype="form-data" action="/createReview" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <input type="hidden" name="place_id" value="${place.id}"/>
                        <textarea class="message-content" type="text"  name="message_content" placeholder="Оставьте свой отзыв о заведении" name="text" id="text"></textarea>
                        <select type="hidden" class="form-control mb-2" name="rating" >
                            <#list rating as r>
                                <option value="${r}">${r}</option>
                            </#list>
                        </select>
                        <div class = "add-message">
                            <button type="button" id="add-message">Отправить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <#if authorized??>
            <button type="button" onclick = "showModalWin()" class="add-post-button btn btn-outline-primary" data-toggle="modal" data-target="#modal_window">Оставить отзыв</button>

            <form id="form" name="form" enctype="multipart/form-data" action="/add_new_image" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <input type="hidden" name="place_id" id="place_id" value="${place.id}">
                <div class = "add-place-image">
                    <input type="file" name="place_image" id="image">
                </div>
                <div class = "add-place-image">
                    <button class="btn btn-outline-primary" type="submit">Добавить изображение!</button>
                </div>
                <div>
                    <#if errors?? >
                        <p>Errors in data!</p>
                        <#list errors as fieldError>
                            <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                        </#list>
                    </#if>
                </div>
            </form>
        <#else>
            <p>Чтобы оставить отзыв необходимо пройти авторизацию</p>
        </#if>

        <#if reviews??>
        <table class="table">

            <tbody>
            <thead class="thead-dark">
            <tr>
                <th>#</th>
                <th>Логин</th>
                <th>Комментарий</th>
                <th>Дата</th>
                <th>Время</th>
            </tr>
            </thead>
            <#list reviews as review>
                <thead class="thead-light">
                <tr>
                    <td>${review_index+1}</td>
                    <td>${review.login}</td>
                    <td>${review.content}</td>
                    <td>${review.date}</td>
                    <td>${review.time}</td>
                </tr>
                </thead>
            </#list>
            <#else>
                <h4>Сообщений пока нет</h4>
            </#if>
            </tbody>
        </table>

    </div>
</@main.renderWith>