package com.ibragimova.corecommon.strings

internal val strings = mapOf(
    key(StringKey.ConnectionSuccessMessage) to mapOf(
        SupportedLanguageKey.Ru to "Соединение восстановлено",
        SupportedLanguageKey.En to "Connection restored",
    ),
    key(StringKey.ConnectionErrorMessage) to mapOf(
        SupportedLanguageKey.Ru to "Отсутствует подключение к сети",
        SupportedLanguageKey.En to "No network connection",
    ),
    key(StringKey.UnknownErrorMessage) to mapOf(
        SupportedLanguageKey.Ru to "Произошла ошибка. Попробуйте повторить позднее",
        SupportedLanguageKey.En to "An error has occurred. Try again later",
    ),

    key(StringKey.ActionReload) to mapOf(
        SupportedLanguageKey.En to "Reload",
        SupportedLanguageKey.Ru to "Перезагрузить",
    ),
    key(StringKey.ErrorLoadFail) to mapOf(
        SupportedLanguageKey.En to "Error while loading",
        SupportedLanguageKey.Ru to "Ошибка при загрузке",
    ),

    key(StringKey.MoviesTitle) to mapOf(
        SupportedLanguageKey.En to "Movies",
        SupportedLanguageKey.Ru to "Фильмы",
    ),
    key(StringKey.MoviesTitleUpComing) to mapOf(
        SupportedLanguageKey.En to "Upcoming",
        SupportedLanguageKey.Ru to "Скоро",
    ),
    key(StringKey.MoviesTitlePlayingInTheatres) to mapOf(
        SupportedLanguageKey.En to "Playing In Theatres",
        SupportedLanguageKey.Ru to "Сейчас в кинотеатрах",
    ),
    key(StringKey.MoviesTitlePopular) to mapOf(
        SupportedLanguageKey.En to "Popular",
        SupportedLanguageKey.Ru to "Популярные",
    ),
    key(StringKey.MoviesTitleTopRated) to mapOf(
        SupportedLanguageKey.En to "Top rated",
        SupportedLanguageKey.Ru to "Высокорейтинговые",
    ),
)