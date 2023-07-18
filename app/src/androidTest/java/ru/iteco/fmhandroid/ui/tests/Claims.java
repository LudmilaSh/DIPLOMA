package ru.iteco.fmhandroid.ui.tests;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.AuthorizationAndLogoutSteps;
import ru.iteco.fmhandroid.ui.data.CommonSteps;
import ru.iteco.fmhandroid.ui.data.CreateClaimsSteps;
import ru.iteco.fmhandroid.ui.data.EditClaimsSteps;
import ru.iteco.fmhandroid.ui.data.FilterClaimsSteps;

public class Claims {
    AuthorizationAndLogoutSteps authorizationAndLogoutSteps = new AuthorizationAndLogoutSteps();
    FilterClaimsSteps filterClaimsSteps = new FilterClaimsSteps();
    CommonSteps commonSteps = new CommonSteps();
    EditClaimsSteps editClaimsSteps = new EditClaimsSteps();
    CreateClaimsSteps createClaimsSteps = new CreateClaimsSteps();
    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            commonSteps.userLoggedIn();
        } catch (AssertionError e) {
            authorizationAndLogoutSteps.login();
        }
    }

    public String text = "Claims";
    @Test
    @DisplayName("Свернуть и развернуть заявки на главном экране")
    @Description("При нажатии на заявку, отображенную на главном экране, открывается ее полное содержимое")
    public void shouldOpenAndCloseClaimsOnMain() {
        filterClaimsSteps.openAndCloseClaimsOnMain();
    }

    @Test
    @DisplayName("Переход в раздел Заявки через кнопку ALL CLAIMS на главном экране")
    @Description("При нажатии на кнопку ALL CLAIMS на главном экране пользователь переходит в раздел Заявки")
    public void shouldCheckAllClaims() {
        filterClaimsSteps.checkAllClaims();
    }

    @Test
    @DisplayName("Переход в раздел заявок через трехстрочное меню-гамбургер")
    @Description("При нажатии на кнопку главного меню и кнопку Заявки пользователь переходит в раздел Заявок")
    public void shouldCheckMenuButton() {
        commonSteps.checkMenuButton(text);
    }

    @Test
    @DisplayName("Фильтрация заявок с выбором одного статуса")
    @Description("При нажатии на кнопку фильтра и выборе одного статуса отображаются заявки только с выбранным статусом")
    public void shouldFilterClaimsOneStatus() {
        String text1 = "Open";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
    }

    @Test
    @DisplayName("Фильтрация заявок без статуса")
    @Description("При нажатии на кнопку фильтра и отключении всех чек-боксов на экране пустое поле с надписью Здесь пока ничего нет")
    public void shouldFilterClaimsWithoutStatus() {
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickCheckBoxOpen();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterWithoutStatus();
    }

    @Test
    @DisplayName("Просмотр полной информации о заявке")
    @Description("При нажатии на заявку отображается полное ее содержимое")
    public void shouldOpenFullInfoAboutClaims() {
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.fullInfoAboutClaims();
    }

    @Test
    @DisplayName("Редактирование комментария к заявке")
    @Description("При нажатии на кнопку редактирования комментария и исправлении содержимого исправления сохраняются")
    public void shouldEditClaimComment() {
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.fullInfoAboutClaims();
        editClaimsSteps.editCommentClaims();
    }

    @Test
    @DisplayName("Добавление комментария к заявке")
    @Description("При нажатии на кнопку добавления комментария и сохранении информации комментарий добавляется")
    public void shouldAddComment() {
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.fullInfoAboutClaims();
        editClaimsSteps.addComment();
    }

    @Test
    @DisplayName("Добавление комментария с пустым текстовым полем")
    @Description("При сохранении нового комментария с пустым полем появляется сообщение, что поле не может быть пустым")
    public void shouldErrorAddCommentWithoutText() {
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.fullInfoAboutClaims();
        editClaimsSteps.addCommentWithoutText();
    }

    @Test
    @DisplayName("Отмена добавления комментария")
    @Description("При добавлении нового комментария и нажатии на кнопку Отмены комментарий не сохраняется")
    public void shouldCancelAddComment() {
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.fullInfoAboutClaims();
        editClaimsSteps.cancelAddComment();
    }

    @Test
    @DisplayName("Смена статуса заявки с Открыта на статус В работе")
    @Description("При нажатии на кнопку смены статуса Открытой заявки на статус В работе статус заявки меняется")
    public void shouldChangeOpenStatusToInProgress() {
        String text1 = "Open";
        String status1 = "take to work";
        String status2 = "In progress";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickChangeStatusButton();
        editClaimsSteps.changeOpenStatusClaims(status1, status2);
    }

    @Test
    @DisplayName("Смена статуса заявки с Открыта на статус Отменена")
    @Description("При нажатии на кнопку смены статуса Открытой заявки на статус Отменена статус заявки меняется")
    public void shouldChangeOpenStatusToCancel() {
        String text1 = "Open";
        String status1 = "Cancel";
        String status2 = "Canceled";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickChangeStatusButton();
        editClaimsSteps.changeOpenStatusClaims(status1, status2);
    }

    @Test
    @DisplayName("Смена статуса заявки с В работе на статус Выполнена")
    @Description("При нажатии на кнопку смены статуса заявки В работе на статус Выполнена статус заявки меняется")
    public void shouldChangeInProgressStatusToExecute() {
        String text1 = "In progress";
        String status1 = "To execute";
        String status2 = "Executed";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxOpen();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickChangeStatusButton();
        editClaimsSteps.changeInProgressStatusClaims(status1, status2);
    }

    @Test
    @DisplayName("Смена статуса заявки с В работе на статус Открыта(нажать Сбросить при смене статуса)")
    @Description("При нажатии на кнопку смены статуса заявки В работе на Сбросить статус заявки меняется на Открыта")
    public void shouldChangeInProgressStatusToOpen() {
        String text1 = "In progress";
        String status1 = "Throw off";
        String status2 = "Open";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxOpen();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickChangeStatusButton();
        editClaimsSteps.changeInProgressStatusClaims(status1, status2);
    }

    @Test
    @DisplayName("Уведомление о возможности редактирования заявки только в статусе Открыта для заявки в статусе Отменена")
    @Description("При нажатии на кнопку редактирования Отмененной заявки появляется сообщение," +
            "что редактировать можно только заявки в статусе Открыта")
    public void shouldNotEditCancelledClaim() {
        String text1 = "Canceled";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickCheckBoxOpen();
        filterClaimsSteps.clickCheckBoxCancelled();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickEditClaimButton();
        editClaimsSteps.editClaimNotInOpenStatus();
    }

    @Test
    @DisplayName("Уведомление о возможности редактирования заявки только в статусе Открыта для заявки в статусе Выполнена")
    @Description("При нажатии на кнопку редактирования Выполненной заявки появляется сообщение," +
            "что редактировать можно только заявки в статусе Открыта")
    public void shouldNotEditExecutedClaim() {
        String text1 = "Executed";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickCheckBoxOpen();
        filterClaimsSteps.clickCheckBoxExecuted();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickEditClaimButton();
        editClaimsSteps.editClaimNotInOpenStatus();
    }

    @Test
    @DisplayName("Уведомление о возможности редактирования заявки только в статусе Открыта для заявки в статусе В работе")
    @Description("При нажатии на кнопку редактирования заявки В работе появляется сообщение," +
            "что редактировать можно только заявки в статусе Открыта")
    public void shouldNotEditInProgressClaim() {
        String text1 = "In progress";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxOpen();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickEditClaimButton();
        editClaimsSteps.editClaimNotInOpenStatus();
    }

    @Test
    @DisplayName("Возможность редактирования заявки в статусе Открыта")
    @Description("При нажатии на кнопку редактрования Открытой заявки пользователь переходит на экран редактирования заявки")
    public void clickableEditOpenClaim() {
        String text1 = "Open";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickableEdit();
    }

    @Test
    @DisplayName("Удаление заголовка при редактировании заявки в статусе Открыта")
    @Description("При переходе на экранн редактирования заявки, удалении заголовка и нажатии на кнопку Сохранить" +
            "появляется сообщение Пустое поле, исправления не сохраняются")
    public void deletingTitleOpenClaim() {
        String text1 = "Open";
        String title = " ";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickEditClaimButton();
        editClaimsSteps.changeTitle(title);
        editClaimsSteps.emptyTitleEditClaim();
    }

    @Test
    @DisplayName("Редактирование заголовка при редактировании заявки в статусе Открыта")
    @Description("При переходе на экранн редактирования заявки, исправлении заголовка и нажатии на кнопку Сохранить" +
            "исправления сохраняются")
    public void changeTitleOpenClaim() {
        String text1 = "Open";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickEditClaimButton();
        editClaimsSteps.editClaim();
    }

    @Test
    @DisplayName("Появление предупреждающего сообщения при отмене редактирования заявки")
    @Description("Если после редактирования заявки нажать на кнопку Отмены, то появится сообщение" +
            "Изменения не будут сохранены, вы действительно хотите выйти?")
    public void massageCancelEditClaim() {
        String text1 = "Open";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickEditClaimButton();
        editClaimsSteps.massageCancelEditClaim();
    }

    @Test
    @DisplayName("Отмена редактирования заявки")
    @Description("При нажатии на кнопку ОК при отмене редактирования заявки изменения не сохраняются ")
    public void shouldCancelEditClaim() {
        String text1 = "Open";
        commonSteps.checkMenuButton(text);
        filterClaimsSteps.checkFilterButton();
        filterClaimsSteps.clickCheckBoxInProgress();
        filterClaimsSteps.clickOkButton();
        filterClaimsSteps.filterClaimsOneStatus(text1);
        editClaimsSteps.clickEditClaimButton();
        editClaimsSteps.massageCancelEditClaim();
        editClaimsSteps.cancelEditClaim();
    }

    @Test
    @DisplayName("Создание заявки на главном экране")
    @Description("При нажатии на кнопку добавления заявки на главном экране и сохранении информации, заявка создается")
    public void shouldCreateClaimOnMain() {
        createClaimsSteps.createClaim();
    }

    @Test
    @DisplayName("Создание новой заявки в разделе Заявки")
    @Description("При переходе в раздел заявки через трехстрочное меню, нажатии на кнопку добавления заявки" +
            " и сохранении информации, заявка создается")
    public void shouldCreateClaimOnClaims() {
        commonSteps.checkMenuButton(text);
        createClaimsSteps.createClaim();
    }

    @Test
    @DisplayName("Создание новой заявки с пустыми текстовыми полями")
    @Description("При сохранении новой заявки с пустыми текстовыми полями появляется сообщение Заполните пустые поля")
    public void shouldNotCreateClaimWithEmptyFields() {
        commonSteps.checkMenuButton(text);
        createClaimsSteps.createClaimWithEmptyFields();
    }

    @Test
    @DisplayName("Создание новой заявки с заголовком более 50 символов")
    @Description("Если при создании новой заявки ввести в поле Тема более 50 знаков, то сохранится только первые 50")
    public void shouldNotCreateClaimWithTitleMore50() {
        commonSteps.checkMenuButton(text);
        createClaimsSteps.createClaimWithTitleMore50();
    }

    @Test
    @DisplayName("Уведомление о несохранении данных при отмене создания заявки")
    @Description("Если после создании заявки нажать на кнопку Отмены, то появится сообщение" +
            "Изменения не будут сохранены, вы действительно хотите выйти?")
    public void shouldMassageCancelCreateClaim() {
        commonSteps.checkMenuButton(text);
        createClaimsSteps.massageCancelCreateClaim();
    }

    @Test
    @DisplayName("Отмена создания заявки")
    @Description("Если при создании заявки нажать на кнопку Отмены и потом ОК, то заявка не сохраняется")
    public void shouldCancelCreateClaim() {
        commonSteps.checkMenuButton(text);
        createClaimsSteps.massageCancelCreateClaim();
        createClaimsSteps.cancelCreateClaim();
    }

    @Test
    @DisplayName("Создание заявки с ручным вводом невалидного значения времени")
    @Description("Если при создании заявки в поле Время ввести вручную невалидное время, то появится сообщение" +
            "Введите валидное время")
    public void shouldNotCreateClaimWithInvalidTime() {
        commonSteps.checkMenuButton(text);
        createClaimsSteps.createClaimWithInvalidTime();
    }

}
