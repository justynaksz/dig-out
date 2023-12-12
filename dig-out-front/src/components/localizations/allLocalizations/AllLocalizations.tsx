import React from 'react'
import { useState, useEffect } from 'react';
import api from '../../../api/axiosConfig.js'
import { LocalizationsList } from '../localizationsList/LocalizationsList.tsx';
import { LocalizationType } from '../localization/Localization.tsx';

export const AllLocalizations = () => {

    const [localizations, setLocalizations] = useState<LocalizationType[]>([] as LocalizationType[]);

    const getLocalizations = async () => {
        try {
            const response = await api.get("/localizations");
            setLocalizations(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getLocalizations();
        }, [])

  return (
    <div>
        <h4 className='title'>ALL LOCALIZATIONS</h4>
        <LocalizationsList localizations={localizations}/>
    </div>
  )
}
