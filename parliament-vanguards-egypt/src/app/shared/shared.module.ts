import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FormComponents } from './components/form';
import { DataGridComponent } from './components/data-grid/data-grid.component';
import { RouterModule } from '@angular/router';
import { DeleteDialogComponent } from './components/delete-dialog/delete-dialog.component';
import { NumbersOnlyDirective } from './directives/numbers-only.directive';
import { EnglishCharOnlyDirective } from './directives/english-char-only.directive';
import { ArabicCharOnlyDirective } from './directives/arabic-char-only.directive';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MaterialModuleModule } from './material-module/material-module.module';




@NgModule({
    imports: [
        CommonModule,
        MaterialModuleModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        MatIconModule,
        MatTooltipModule,
        RouterModule.forChild([]),
        ...FormComponents,
        DataGridComponent,
        NumbersOnlyDirective,
        EnglishCharOnlyDirective,
        ArabicCharOnlyDirective,
        DataGridComponent,
        DeleteDialogComponent,
        NumbersOnlyDirective,
        EnglishCharOnlyDirective,
        ArabicCharOnlyDirective,
        DataGridComponent,
        DeleteDialogComponent,
    ],
    exports: [
        ...FormComponents,
        CommonModule,
        MaterialModuleModule,
        FormsModule,
        ReactiveFormsModule,
        MatTooltipModule,
        HttpClientModule,
        DataGridComponent,
        DeleteDialogComponent,
        MatIconModule,
    ]
})
export class SharedModule {}
