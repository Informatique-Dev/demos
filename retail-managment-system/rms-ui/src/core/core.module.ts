import { NgModule, Optional, SkipSelf } from '@angular/core';

import { RouterModule } from '@angular/router';
// import { MaterialModuleModule } from './material-module/material-module.module';

@NgModule({
  declarations: [],
  imports: [
    // MaterialModuleModule,

    RouterModule,
  ],
  exports: [],
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only'
      );
    }
  }
}
