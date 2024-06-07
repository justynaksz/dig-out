import React from 'react';

import { LocalizationByIdForm } from '../../components/localizations/localizationByIdForm/LocalizationByIdForm.tsx';
import { NewLocalizationForm } from '../../components/localizations/newLocalizationForm/NewLocalizationForm.tsx';
import { RemoveLocalization } from '../../components/localizations/removeLocalization/RemoveLocalization.tsx';
import { AllLocalizations } from '../../components/localizations/allLocalizations/AllLocalizations.tsx';
import  '../Pages.scss';
import { BirthdateCarousel } from '../../components/carousel/birth/BirthdateCarousel.tsx';



export const Localizations = () => {

  return (
    <>
      <BirthdateCarousel/>
    </>
  )
}
