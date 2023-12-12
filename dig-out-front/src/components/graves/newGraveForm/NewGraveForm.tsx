import React, { ChangeEvent, FormEvent, useState } from 'react';
import api from '../../../api/axiosConfig.js';
import { GraveType } from '../grave/Grave.tsx';
import { LocalizationType } from '../../localizations/localization/Localization.tsx';


export const NewGraveForm = () => {
    const [grave, setGrave] = useState<GraveType>({} as GraveType);
    const [localization, setLocalization] = useState<LocalizationType>({} as LocalizationType); 

    const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setGrave((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleLocalizationInput = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setLocalization((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setGrave(prev => ({
            ...prev,
            localization: localization
        }));
        addGrave({
            ...grave,
            id: '0',
        });
    };

    const addGrave = async (newGrave: GraveType) => {
        try {
            const createdGrave = newGrave;
            await api.post("/graves", createdGrave);
        } catch(error) {
            console.log(error);
        };
    };

    return (
        <>
            <h4 className='title'> CREATE NEW GRAVE </h4>
            <form className='body' onSubmit={handleSubmit}>
                <label htmlFor='type'>
                    <input
                    type='text'
                    id='type'
                    name='type'
                    value={grave?.type || ''}
                    onChange={handleInput}
                    placeholder='Type'
                    />
                </label>
                <label htmlFor='cemetery'>
                <input
                    type='text'
                    id='cemetery'
                    name='cemetery'
                    value={localization?.cemetery || ''}
                    onChange={handleLocalizationInput}
                    placeholder='Cemetery'
                />
                </label>
                <label htmlFor='quarter'>
                <input
                    type='text'
                    id='quarter'
                    name='quarter'
                    value={localization?.quarter || ''}
                    onChange={handleLocalizationInput}
                    placeholder='Quarter'
                />
                </label>
                 <label htmlFor='localizationRow'>
                <input
                    type='text'
                    id='localizationRow'
                    name='localizationRow'
                    value={localization?.localizationRow || ''}
                    onChange={handleLocalizationInput}
                    placeholder='Row'
                />
                </label>
                <label htmlFor='localizationColumn'>
                <input
                    type='text'
                    id='localizationColumn'
                    name='localizationColumn'
                    value={localization?.localizationColumn || ''}
                    onChange={handleLocalizationInput}
                    placeholder='Column'
                />
                </label>
                <label htmlFor='owner'>
                <input
                    type='text'
                    id='graveOwner'
                    name='graveOwner'
                    value={grave?.graveOwner || ''}
                    onChange={handleInput}
                    placeholder='Owner'
                />
                </label>
            <button className='button' type='submit'>Add grave</button>
            </form>
        </>
    )
}