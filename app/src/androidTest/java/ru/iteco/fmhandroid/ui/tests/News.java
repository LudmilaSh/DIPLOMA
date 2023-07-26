package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationAndLogoutSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.EditCreateNewsSteps;
import ru.iteco.fmhandroid.ui.steps.FilterSortingNewsSteps;
import ru.iteco.fmhandroid.ui.data.ScreenshotTestRule;

@RunWith(AllureAndroidJUnit4.class)
public class News {
    FilterSortingNewsSteps filterSortingNewsSteps = new FilterSortingNewsSteps();
    EditCreateNewsSteps editCreateNewsSteps = new EditCreateNewsSteps();
    CommonSteps commonSteps = new CommonSteps();
    AuthorizationAndLogoutSteps authorizationAndLogoutSteps = new AuthorizationAndLogoutSteps();
    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

    @Before
    public void setUp() {
        try {
            commonSteps.userLoggedIn();
        } catch (AssertionError e) {
            authorizationAndLogoutSteps.login();
        }
    }

    public String text = "News";
    @Test
    @DisplayName("Сворачивание и разворачивание списка новостей на главном экране")
    @Description("При нажатии на новость открывается полное ее содержимое")
    public void shouldOpenAndCloseNewsOnMain() {
        filterSortingNewsSteps.openAndCloseNewsOnMain();
    }

    @Test
    @DisplayName("Переход в раздел Новостей через кнопку ALL NEWS на главном экране")
    @Description("При нажатии на кнопку ALL NEWS на главном экране осуществляется переход в раздел новостей")
    public void shouldCheckAllNews() {
        filterSortingNewsSteps.checkAllNews();
    }

    @Test
    @DisplayName("Переход в раздел новостей через трехстрочное меню-гамбургер")
    @Description("При нажатии на кнопку главного меню и кнопку Новости пользователь переходит в раздел новостей")
    public void shouldCheckMenuButton() {
        commonSteps.checkMenuButton(text);
    }

    @Test
    @DisplayName(" Сортировка новостей от новых к старым")
    @Description("При нажатии на кнопку сортировки новостей один раз первыми на экране появляются самые свежие новости")
    public void shouldDateSortingFromNew() {
        commonSteps.checkMenuButton(text);
        filterSortingNewsSteps.dateSortingOneClick();
    }

    @Test //упадет, фильтрация работает неправильно
    @DisplayName(" Сортировка новостей от старых к новым")
    @Description("При нажатии на кнопку сортировки новостей второй раз первыми на экране появляются самые старые новости")
    public void shouldDateSortingFromPast() {
        commonSteps.checkMenuButton(text);
        filterSortingNewsSteps.dateSortingDoubleClick();
    }

    @Test
    @DisplayName(" Сортировка новостей от новых к старым в разделе редактирования")
    @Description("При нажатии на кнопку сортировки новостей в разделе редактирования один раз первыми на экране появляются самые первые новости")
    public void shouldDateSortingFromNewEditingSection() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        filterSortingNewsSteps.dateSortingOneClickControlPanel();
    }

    @Test
    @DisplayName(" Сортировка новостей от старых к новым в разделе редактирования")
    @Description("При нажатии на кнопку сортировки новостей в разделе редактирования второй раз первыми на экране появляются самые старые новости")
    public void shouldDateSortingFromPastEditingSection() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        filterSortingNewsSteps.dateSortingDoubleClickControlPanel();
    }

    @Test
    @DisplayName("Фильтрация новостей с датой из будущего")
    @Description("При фильтрации новостей с выбором даты из будущего отображается экран с надписью Здесь пока ничего нет")
    public void shouldFilterDateFromFuture() {
        commonSteps.checkMenuButton(text);
        filterSortingNewsSteps.filterDateFromFuture();
    }

    @Test
    @DisplayName("Фильтрация новостей с выбором валидной даты, без выбора категории")
    @Description("При фильтрации новостей с выбором валидной даты отображаются новости только за выбранный период")
    public void shouldFilterValidDate() {
        commonSteps.checkMenuButton(text);
        filterSortingNewsSteps.filterDate();
    }


    @Test
    @DisplayName("Отмена фильтрации новостей")
    @Description("При отмене фильтрации новостей на экране отображаются новостив том же порядке как и до фильтрации")
    public void shouldCancelFilter() {
        commonSteps.checkMenuButton(text);
        filterSortingNewsSteps.cancelFilter();
    }

    @Test
    @DisplayName("Переход в раздел редактирования новостей")
    @Description("При нажатии на кнопку добавления новости пользователь попдает в раздел редактирования новостей")
    public void shouldCheckEditNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
    }

    @Test
    @DisplayName("Фильтрация новостей со статусом Активна")
    @Description("При фильтрации новостей со статусом Активна на экране отображается список новостей только со статусом Активна")
    public void shouldFilterActiveNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.filterActiveNews();
    }

    @Test
    @DisplayName("Фильтрация новостей со статусом Неактивна")
    @Description("При фильтрации новостей со статусом Неактивна на экране отображается список новостей только со статусом Неактивна")
    public void shouldFilterNotActiveNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.filterNotActiveNews();
    }

    //Должен упасть, т.к. при невыбранном статусе кнопка Фильтр должна быть неактивна
    @Test
    @DisplayName("Фильтрация новостей без статуса в разделе Панель управления")
    @Description("При фильтрации новостей без выбора статуса кнопка фильтра должна быть некликабельна")
    public void shouldNotFilterWithoutStatus() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.filterWithoutStatus();
    }

    @Test
    @DisplayName("Фильтрация новостей с датой из будущего в разделе Панель управления")
    @Description("При фильтрации новостей в разделе редактирования с выбором даты из будущего отображается экран с надписью Здесь пока ничего нет")
    public void shouldFilterDateFromFutureInControlPanel() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.filterDateFromFutureInControlPanel();
    }

    @Test
    @DisplayName("Создание новости")
    @Description("При создании новости со всеми валидно заполненными полями новость появляется в списке")
    public void shouldCreateNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNews();
    }

    @Test
    @DisplayName("Создание новости с незаполненным полем категория")
    @Description("При создании новости с незаполненным полем Категория появляется уведомление Заполните пустые поля")
    public void shouldNotCreateNewsWithoutCategory() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNewsWithoutCategory();
    }

    @Test
    @DisplayName("Создание новости с незаполненным полем заголовок")
    @Description("При создании новости с незаполненным полем Заголовок появляется уведомление Заполните пустые поля")
    public void shouldNotCreateNewsWithoutTitle() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNewsWithoutTitle();
    }

    @Test
    @DisplayName("Создание новости с незаполненным полем описание")
    @Description("При создании новости с незаполненным полем Описание появляется уведомление Заполните пустые поля")
    public void shouldNotCreateNewsWithoutDescription() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNewsWithoutDescription();
    }

    @Test
    @DisplayName("Создание новости с незаполненными полями дата и время")
    @Description("При создании новости с незаполненными полями Дата и Время появляется уведомление Заполните пустые поля")
    public void shouldNotCreateNewsWithoutDateAndTime() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNewsWithoutDateAndTime();
    }

    @Test
    @DisplayName("Создание новости с текстовыми полями, заполненными спецсимволами")
    @Description("При создании новости с текстовыми полями, заполненными спецсимволами, " +
            "появляется ошибка Сохранение не удалось, попробуйте позднее ")
    public void shouldNotCreateNewsWithSymbols() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNewsWithSymbols();
    }

    @Test
    @DisplayName("Создание новости с датой публикации из будущего")
    @Description("При создании новости с датой публикации из будущего новость появляется в списке в разделе редактирования")
    public void shouldCreateNewsFromFuture() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNewsFromFuture();
    }

    @Test
    @DisplayName("Проверка, что кнопка статуса неактивна при создании новости")
    @Description("При создании новости кнопка смены статуса неактивна")
    public void checkBoxNotActive() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.clickAddNews();
        editCreateNewsSteps.notActiveCheckBoxStatus();
    }

    @Test
    @DisplayName("Создание новости с ручным вводом невалидного значения времени")
    @Description("При создании новости с ручным вводом невалидного значения времени появляется сообщение" +
            "Введите правильное время")
    public void shouldNotCreateNewsWithInvalidTime() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNewsWithInvalidTime();
    }


    //При ручном тестировании нет возможности ввести дату из прошлого
    @Test //должен упасть
    @DisplayName("Создание новости с датой публикации из прошлого")
    @Description("При создании новости с датой публикации из прошлого новость не должна создаваться")
    public void shouldNotCreateNewsWithInvalidDate() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.createNewsFromPast();
    }

    @Test
    @DisplayName("Предупреждение об отмене создания новости")
    @Description("При отмене сожранения новой новости появляется предупреждение" +
            "Изменения не будут сохранены. Вы действительно хотите выйти?")
    public void shouldMessageCancelCreateNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.warningMessageCancelCreateNews();
    }

    @Test
    @DisplayName("Отмена создания новости")
    @Description("При отмене сохранения при создании новости новость не создается")
    public void shouldCancelCreateNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.cancelCreateNews();
    }

    @Test
    @DisplayName("Предупреждение при удалении существующей новости")
    @Description("При удалении существующей новости появляется предупреждение" +
            "Вы уверены, что хотите безвозвратно удалить документ? Данные изменения нельзя будет отменить в будущем.")
    public void shouldMassageDeleteNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.warningMassageDeleteExistingNews();
    }

    @Test
    @DisplayName("Удаление существующей новости")
    @Description("При подтверждении удаления существующей новости новость удаляется")
    public void shouldDeleteNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.deleteNews();
    }

    @Test
    @DisplayName("Редактирование новости с пустыми полями")
    @Description("Если при редактировании оставить пустыми текстовые поля, то при сохранении появится уведомление" +
            "Заполните пустые поля")
    public void shouldNotEditNewsWithEmptyFields() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.editNewsWithEmptyFields();
    }

    @Test
    @DisplayName("Сообщение с предупреждением об отмене редактирования новости")
    @Description("При отмене редактирования существующей новости появляется предупреждение" +
            "Изменения не будут сохранены. Вы действительно хотите выйти?")
    public void shouldMessageCancelEditNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.messageCancelEditNews();
    }

    @Test
    @DisplayName("Возможность редактирования всех полей существующей новости")
    @Description("При редактировании новости есть возможность изменить все поля существующей новости")
    public void shouldEditAllFieldsNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.editAllFieldsNews();
    }

    @Test
    @DisplayName("Возможность изменить статус существующей новости")
    @Description("При смене статуса существующей новости статус новости меняется")
    public void shouldEditStatusNews() {
        commonSteps.checkMenuButton(text);
        editCreateNewsSteps.checkEdit();
        editCreateNewsSteps.editStatusNews();
    }
}
